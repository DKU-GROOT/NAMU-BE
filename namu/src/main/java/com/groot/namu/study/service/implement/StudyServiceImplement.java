package com.groot.namu.study.service.implement;

import com.groot.namu.study.dto.request.*;
import com.groot.namu.study.dto.response.*;
import com.groot.namu.study.entity.*;
import com.groot.namu.study.repository.ExamRepository;
import com.groot.namu.study.repository.SummaryRepository;
import com.groot.namu.study.repository.UserQuestionRepository;
import com.groot.namu.study.repository.UserSubjectRepository;
import com.groot.namu.study.service.StudyService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.*;

@Service
@RequiredArgsConstructor
public class StudyServiceImplement implements StudyService {

    private static final Logger logger = LoggerFactory.getLogger(StudyServiceImplement.class);
    private final SummaryRepository summaryRepository;
    private final UserSubjectRepository userSubjectRepository;
    private final UserQuestionRepository userQuestionRepository;
    private final ExamRepository examRepository;
    private final WebClient.Builder webClientBuilder;
    @Value("${openai.api.key}")
    private String openAiApiKey;

//    @Override
//    public ResponseEntity<? super ShowMyStudyResponseDto> showMyStudy(ShowMyStudyRequestDto dto) {
//
//    }

    @Override
    public ResponseEntity<? super ScoringResponseDto> Scoring(ScoringRequestDto dto){
        int examId = dto.getExamId();
        int score = 0;
        String[] result = new String[5];
        ExamEntity user_exam = examRepository.findByExamId(examId);

        if(user_exam.getSolution1().trim().equals(dto.getUserAnswer1().trim())){
            score += 20;
            result[0] = "O";
        }else {
            result[0] = "X";
        }

        if(user_exam.getSolution2().trim().equals(dto.getUserAnswer2().trim())){
            score += 20;
            result[1] = "O";
        }else {
            result[1] = "X";
        }

        if(user_exam.getSolution3().trim().equals(dto.getUserAnswer3().trim())){
            score += 20;
            result[2] = "O";
        }else {
            result[2] = "X";
        }

        if(user_exam.getSolution4().trim().equals(dto.getUserAnswer4().trim())){
            score += 20;
            result[3] = "O";
        }else {
            result[3] = "X";
        }

        if(user_exam.getSolution5().trim().equals(dto.getUserAnswer5().trim())){
            score += 20;
            result[4] = "O";
        }else {
            result[4] = "X";
        }

        ExamEntity existingExam = examRepository.findByExamId(examId);
        user_exam.setScore(score);
        user_exam.setResult(result);
        examRepository.save(user_exam);

        return ScoringResponseDto.success(score, result);
    }

    @Override
    public ResponseEntity<? super ExamResponseDto> exam(ExamRequestDto dto){
        String email = dto.getEmail();
        String subjectName = dto.getSubjectName();

        SummaryEntity mySummary = summaryRepository.findByEmailAndSubjectName(email, subjectName);

        List<Map<String, String>> messages = new ArrayList<>();
        messages.add(Map.of("role", "system", "content", "너는"+ subjectName+ "과목의" +mySummary.getSummary()+"\n 이 요약본 내용에 대한 시험을 출제해줘야해."));

        messages.add(Map.of("role", "user", "content", mySummary.getSummary()+
                "\n\n"+subjectName+ "과목의 요약 내용을 바탕으로 빈칸 뚫어놓기 유형의 시험 문제 5개를 출제해줘. "
                +"문제 예시 : 문제2 답안 : ________은 작은 단위로 나눠 락을 걸어 동시에 실행되지만 오버헤드가 증가하고 관리가 복잡할 수 있다."
                +"예시일 뿐이고" + subjectName + "과목의 요약본인 " + mySummary.getSummary() + "를 읽어보고 관련된 문제를 출제해주면 되는거야."
                +"내가 DB에 저장할 때 쉽게 하기위해 아래 양식을 지켜서 응답해주면 돼.\n"
                +"문제 1 : 내용\n"
                +"====================\n"
                +"1번 정답\n"
                +"====================\n"
                +"문제2 : 내용\n"
                +"====================\n"
                +"2번 정답\n"
                +"====================\n"
                +"문제3 : 내용\n"
                +"====================\n"
                +"3번 정답\n"
                +"====================\n"
                +"문제4 : 내용\n"
                +"====================\n"
                +"4번 정답\n"
                +"====================\n"
                +"문제5 :내용\n"
                +"====================\n"
                +"5번 정답\n"
                +"====================\n"
                +"그렇다고 예시대로 보내지 말고, 실제로는 내용이라고 적혀있는 부분과 ~번 정답이라고 적힌 부분에 내용을 추가해줘야해!! 그리고 꼭 빈칸채우기 문제라는 점을 명심해줘"
                +"또한 각 문제와 각 문제의 답안 사이에 ==================== 이 문장을 추가해줘."
        ));

        WebClient webClient = webClientBuilder.baseUrl("https://api.openai.com").build();

        ExamResponseDto examResponse;
        try {
            examResponse = webClient.post()
                    .uri("/v1/chat/completions")
                    .header("Authorization", "Bearer " + openAiApiKey)
                    .header("Content-Type", "application/json")
                    .bodyValue(Map.of(
                            "model", "gpt-4o",
                            "messages", messages,
                            "max_tokens", 1000
                    ))
                    .retrieve()
                    .bodyToMono(ExamResponseDto.class)
                    .block();
        } catch (Exception e) {
            logger.error("Error occurred: ", e);
            System.out.println("WebClient 오류발생");
            return ExamResponseDto.examFail();
        }

        assert examResponse != null;
        String test = examResponse.getChoices().get(0).getMessage().getContent();
        Map<String, String> examQuestions = getContentAsMap(test);

        ExamEntity examEntity = new ExamEntity(0,email, subjectName,
                examQuestions.get("quiz1"), examQuestions.get("solution1"),
                examQuestions.get("quiz2"), examQuestions.get("solution2"),
                examQuestions.get("quiz3"), examQuestions.get("solution3"),
                examQuestions.get("quiz4"), examQuestions.get("solution4"),
                examQuestions.get("quiz5"), examQuestions.get("solution5"),
                0, null
        );
        examRepository.save(examEntity);

        return ExamResponseDto.success(
                examEntity.getExamId(),
                examEntity.getQuiz1(),
                examEntity.getQuiz2(),
                examEntity.getQuiz3(),
                examEntity.getQuiz4(),
                examEntity.getQuiz5()
        );
    }

    private Map<String, String> getContentAsMap(String test) {
        String[] sections = test.split("====================");
        Map<String, String> map = new HashMap<>();

        for (int i = 0; i < sections.length - 1; i += 2) {
            String questionKey = "quiz" + (i / 2 + 1);
            String answerKey = "solution" + (i / 2 + 1);

            // map the question and the corresponding answer
            map.put(questionKey, sections[i].trim());
            map.put(answerKey, sections[i + 1].trim());
        }
        return map;
    }


    @Override
    public ResponseEntity<? super SummaryResponseDto> summary(SummaryRequestDto dto){
        String email = dto.getEmail();
        String subjectName = dto.getSubjectName();
        List<UserQuestionEntity> previousQuestions = userQuestionRepository.findByEmailAndSubjectName(email, subjectName);

        List<Map<String, String>> messages = new ArrayList<>();
        messages.add(Map.of("role", "system", "content", "당신은 " + subjectName + " 과목을 요약정리를 하기 위한 AI입니다!"));

        for (UserQuestionEntity userQuestionEntity : previousQuestions) {
            messages.add(Map.of("role", "user", "content", userQuestionEntity.getQuestion()));
            messages.add(Map.of("role", "assistant", "content", userQuestionEntity.getAnswer()));
        }

        messages.add(Map.of("role", "user", "content",
                "==============="
                +"이전까지의 내용을 요약해주세."
                +"각 주제와 내용 형태로 요약해주세요."
                +"다만, 내용은 조금은 구체적으로 적을 필요가 있습니다."
        ));

        WebClient webClient = webClientBuilder.baseUrl("https://api.openai.com").build();

        SummaryResponseDto summaryResponse;
        try {
            summaryResponse = webClient.post()
                    .uri("/v1/chat/completions")
                    .header("Authorization", "Bearer " + openAiApiKey)
                    .header("Content-Type", "application/json")
                    .bodyValue(Map.of(
                            "model", "gpt-4o",
                            "messages", messages,
                            "max_tokens", 1000
                    ))
                    .retrieve()
                    .bodyToMono(SummaryResponseDto.class)
                    .block();
        } catch (Exception e) {
            logger.error("Error occurred: ", e);
            System.out.println("WebClient 오류발생");
            return SummaryResponseDto.summaryFail();
        }

        if (summaryResponse == null || summaryResponse.getChoices() == null || summaryResponse.getChoices().isEmpty()) {
            System.out.println("ChatGPT 응답이 비어있음");
            return ChatResponseDto.questionFail();
        }

        String summaryText = summaryResponse.getChoices().get(0).getMessage().getContent();
        SummaryEntity existingSummary = summaryRepository.findByEmailAndSubjectName(email, subjectName);

        if(existingSummary!=null){
            existingSummary.setSummary(summaryText);
            summaryRepository.save(existingSummary);
        }else {
            SummaryEntity newSummaryEntity = new SummaryEntity(null, email, subjectName, summaryText);
            summaryRepository.save(newSummaryEntity);
        }

        return SummaryResponseDto.success(summaryText);
    }



    @Override
    public ResponseEntity<? super ChatResponseDto> chat(ChatRequestDto dto) {

        String email = dto.getEmail();
        String subjectName = dto.getSubjectName();
        System.out.println(email+" "+subjectName);
        List<UserQuestionEntity> previousQuestions = userQuestionRepository.findByEmailAndSubjectName(email, subjectName);

        List<Map<String, String>> messages = new ArrayList<>();
        messages.add(Map.of("role", "system", "content", "당신은 " + subjectName + " 과목의 교수입니다!"));

        for (UserQuestionEntity userQuestionEntity : previousQuestions) {
            messages.add(Map.of("role", "user", "content", userQuestionEntity.getQuestion()));
            messages.add(Map.of("role", "assistant", "content", userQuestionEntity.getAnswer()));
        }

        String question = dto.getQuestion();
        System.out.println(question);
        messages.add(Map.of("role", "user", "content", question));

        WebClient webClient = webClientBuilder.baseUrl("https://api.openai.com").build();

        ChatResponseDto chatResponse;
        try {
            chatResponse = webClient.post()
                    .uri("/v1/chat/completions")
                    .header("Authorization", "Bearer " + openAiApiKey)
                    .header("Content-Type", "application/json")
                    .bodyValue(Map.of(
                            "model", "gpt-4o",
                            "messages", messages,
                            "max_tokens", 1000
                    ))
                    .retrieve()
                    .bodyToMono(ChatResponseDto.class)
                    .block();
        } catch (Exception e) {
            logger.error("Error occurred: ", e);
            System.out.println("WebClient 오류발생");
            return ChatResponseDto.questionFail();
        }

        if (chatResponse == null || chatResponse.getChoices() == null || chatResponse.getChoices().isEmpty()) {
            System.out.println("ChatGPT 응답이 비어있음");
            return ChatResponseDto.questionFail();
        }

        String answer = chatResponse.getChoices().get(0).getMessage().getContent();

        UserQuestionEntity userQuestion = new UserQuestionEntity(null, email, subjectName, question, answer);
        userQuestionRepository.save(userQuestion);

        return ChatResponseDto.success(answer);
    }

//    public ResponseEntity<? super >
//
//    @Override
//    public ResponseEntity<? super CreateStudyResponseDto> createStudy(CreateStudyRequestDto dto) {
//        String email = dto.getEmail();
//        String subjectName = dto.getSubjectName();
//
//        UserSubjectEntity userSubjectEntity = UserSubjectRepository.findByEmailAndSubjectName(email, subjectName);
//        if(userSubjectEntity!=null){
//            return CreateStudyResponseDto.createFail();
//        } else{
//            userSubjectEntity = new UserSubjectEntity(null, email, subjectName);
//            userSubjectRepository.save(userSubjectEntity);
//        }
//
//        return CreateStudyResponseDto.success();
//    }
}
