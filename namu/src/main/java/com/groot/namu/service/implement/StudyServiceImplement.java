package com.groot.namu.service.implement;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.groot.namu.dto.request.study.StudyChatRequestDto;
import com.groot.namu.dto.response.study.StudyChatResponseDto;
import com.groot.namu.entity.UserQuestionEntity;
import com.groot.namu.repository.UserQuestionRepository;
import com.groot.namu.service.StudyService;
import org.springframework.web.reactive.function.client.WebClient;

import lombok.RequiredArgsConstructor;
import java.util.*;

@Service
@RequiredArgsConstructor
public class StudyServiceImplement implements StudyService {

    private final UserQuestionRepository userQuestionRepository;
    private final WebClient.Builder webClientBuilder; // WebClient.Builder를 주입받음
    @Value("${openai.api.key}")
    private String openAiApiKey; // OpenAI API Key 추가

    @Override
    public ResponseEntity<? super StudyChatResponseDto> studyChat(StudyChatRequestDto dto) {

        String email = dto.getEmail();
        String subjectName = dto.getSubjectName();
        
        List<UserQuestionEntity> previousQuestions = userQuestionRepository.findByEmailAndSubjectName(email, subjectName);

        // 메시지 리스트 생성 및 이전 질문, 답변 추가
        List<Map<String, String>> messages = new ArrayList<>();
        messages.add(Map.of("role", "system", "content", "You are a helpful Computer System Professor."));

        for (UserQuestionEntity userQuestionEntity : previousQuestions) {
            messages.add(Map.of("role", "user", "content", userQuestionEntity.getQuestion()));
            messages.add(Map.of("role", "assistant", "content", userQuestionEntity.getAnswer()));
        }

        String question = dto.getQuestion();
        messages.add(Map.of("role", "user", "content", question));

        WebClient webClient = webClientBuilder
                .baseUrl("https://api.openai.com")  // baseUrl을 여기서 설정
                .build();

        StudyChatResponseDto studyChatResponse;
        try {
            // ChatGPT API 호출
            studyChatResponse = webClient.post()
                    .uri("/v1/chat/completions")
                    .header("Authorization", "Bearer " + openAiApiKey)
                    .header("Content-Type", "application/json")
                    .bodyValue(Map.of(
                            "model", "gpt-3.5-turbo",
                            "messages", messages,
                            "max_tokens", 1000
                    ))
                    .retrieve()
                    .bodyToMono(StudyChatResponseDto.class) // ChatCompletionResponse 대신 StudyChatResponseDto로 바로 매핑
                    .block();
        } catch (Exception e) {
            return StudyChatResponseDto.questionFail(); // API 호출 실패 시 실패 응답 반환
        }

        if (studyChatResponse == null || studyChatResponse.getAnswer() == null || studyChatResponse.getAnswer().isEmpty()) {
            return StudyChatResponseDto.questionFail(); // 응답이 없거나 빈 경우 실패 응답 반환
        }

        String answer = studyChatResponse.getAnswer();

        // UserQuestionEntity 저장
        UserQuestionEntity userQuestion = new UserQuestionEntity(null, email, subjectName, question, answer);
        userQuestionRepository.save(userQuestion);

        // 성공적인 응답을 StudyChatResponseDto로 반환
        return StudyChatResponseDto.success(answer);
    }
}
