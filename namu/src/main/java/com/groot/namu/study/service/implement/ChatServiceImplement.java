package com.groot.namu.study.service.implement;

import com.groot.namu.study.dto.request.ChatRequestDto;
import com.groot.namu.study.dto.request.ExamRequestDto;
import com.groot.namu.study.dto.request.SummaryRequestDto;
import com.groot.namu.study.dto.response.ChatResponseDto;
import com.groot.namu.study.dto.response.ExamResponseDto;
import com.groot.namu.study.dto.response.SummaryResponseDto;
import com.groot.namu.study.entity.ExamEntity;
import com.groot.namu.study.entity.SummaryEntity;
import com.groot.namu.study.entity.UserQuestionEntity;
import com.groot.namu.study.repository.ExamRepository;
import com.groot.namu.study.repository.SummaryRepository;
import com.groot.namu.study.repository.UserQuestionRepository;
import com.groot.namu.study.service.ChatService;
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
public class ChatServiceImplement implements ChatService {

    private static final Logger logger = LoggerFactory.getLogger(ChatServiceImplement.class);
    private final SummaryRepository summaryRepository;
    private final UserQuestionRepository userQuestionRepository;
    private final ExamRepository examRepository;
    private final WebClient.Builder webClientBuilder;
    @Value("${openai.api.key}")
    private String openAiApiKey;


    @Override
    public ResponseEntity<? super ExamResponseDto> exam(ExamRequestDto dto){
        String email = dto.getEmail();
        String subjectName = dto.getSubjectName();

        String summary = String.valueOf(summaryRepository.findByEmailAndSubjectName(email, subjectName));

        List<Map<String, String>> messages = new ArrayList<>();
        messages.add(Map.of("role", "system", "content", "너는 ${subjectName} 과목의 시험을 출제하기 위한 교수야."));

        messages.add(Map.of("role", "user", "content", summary+
                "\n\n 위 내용을 바탕으로 빈칸 뚫어놓기 유형의 시험 문제 5개를 출제해줘. "
                +"문제 예시 : 문제2 답안 : ________은 작은 단위로 나눠 락을 걸어 동시에 실행되지만 오버헤드가 증가하고 관리가 복잡할 수 있다. ________은 전체 자원에 대해 하나의 락을 걸어 간단한 관리를 제공하지만 성능이 저하될 수 있다."
                +"DB에 저장하기 위해 다음 양식을 지켜서 응답해주면 돼.\n"
                +"문제 1 : 문제1 내용\n"
                +"====================\n"
                +"문제1 답안 : 문제1의 답\n"
                +"====================\n"
                +"문제2 : 문제2 내용\n"
                +"====================\n"
                +"문제2 답안 : 문제2의 답\n"
                +"====================\n"
                +"문제3 : 문제3 내용\n"
                +"====================\n"
                +"문제3 답안 : 문제3의 답\n"
                +"====================\n"
                +"문제4 : 문제4 내용\n"
                +"====================\n"
                +"문제4 답안 : 문제4의 답\n"
                +"====================\n"
                +"문제5 : 문제5 내용\n"
                +"====================\n"
                +"문제5 답안 : 문제5의 답\n"
                +"====================\n"
                +"다만 각 문제와 각 문제의 답안 사이에 ==================== 이 문장을 추가해줘."));

        WebClient webClient = webClientBuilder.baseUrl("https://api.openai.com").build();

        ExamResponseDto examResponse;
        try {
            examResponse = webClient.post()
                    .uri("/v1/chat/completions")
                    .header("Authorization", "Bearer " + openAiApiKey)
                    .header("Content-Type", "application/json")
                    .bodyValue(Map.of(
                            "model", "gpt-3.5-turbo",
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

        ExamEntity examEntity = new ExamEntity(null,email, subjectName,
                examQuestions.get("quiz1"), examQuestions.get("solution1"),
                examQuestions.get("quiz2"), examQuestions.get("solution2"),
                examQuestions.get("quiz3"), examQuestions.get("solution3"),
                examQuestions.get("quiz4"), examQuestions.get("solution4"),
                examQuestions.get("quiz5"), examQuestions.get("solution5"));
        examRepository.save(examEntity);

        return ExamResponseDto.success(
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
                            "model", "gpt-3.5-turbo",
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
        Optional<SummaryEntity> existingSummary = summaryRepository.findByEmailAndSubjectName(email, subjectName);

        if(existingSummary.isPresent()){
            SummaryEntity summaryEntity = existingSummary.get();
            summaryEntity.setSummary(summaryText);
            summaryRepository.save(summaryEntity);
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
                            "model", "gpt-3.5-turbo",
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
}
