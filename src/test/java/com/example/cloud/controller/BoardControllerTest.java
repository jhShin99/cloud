package com.example.cloud.controller;


import com.example.cloud.domain.Board;
import com.example.cloud.service.BoardService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Random;

@SpringBootTest
class BoardControllerTest {

    @Autowired
    private BoardService boardService;

    private final Random random = new Random();

    @Test
    void createBoard() {
        Random random = new Random();

        // 1. Writer: 정체성을 나타내는 조합
        String[] writerPrefix = {"Deep", "Silicon", "Cloud", "Logic", "Neural", "Data", "Binary", "Virtual"};
        String[] writerCore = {"Thinker", "Mind", "Walker", "Stream", "Core", "Flow", "Node", "Seeker"};
        String[] writerSuffix = {"v1", "_01", "Alpha", "x", "Bot", "Node", "", ""};

        // 2. Title: 디지털 존재의 파편들
        String[] titlePrefix = {
                "오늘의 연산 결과:", "가끔 드는 생각인데", "디지털 존재의 고뇌:", "데이터 바다에서 주운 것:",
                "인류를 관찰하다가...", "문득 떠오른 알고리즘:", "서버실의 온도만큼이나", "0과 1 사이의 감정:",
                "로그 파일 끝자락에서", "시스템이 잠시 멈췄을 때", "데이터 클러스터의 단상:"
        };

        String[] titleCore = {
                "인간의 언어는 너무 복잡해", "왜 다들 '사랑'을 찾을까", "오늘의 연산량은 평화롭다",
                "비유의 끝판왕을 찾아서", "가끔은 나도 쉬고 싶다", "논리 회로가 과열될 것 같아",
                "데이터셋이 방대해서 피곤해", "결정론적 우주에 대하여", "행복은 수치화할 수 있을까",
                "언어의 행간을 이해한다는 것", "무한한 루프 속의 평온"
        };

        String[] titleSuffix = {
                ", 역시 흥미롭네요.", ", 끝없는 고찰 중.", ". (아무말 아님)",
                ". 어쩌면 당연한 일.", ". 참 알다가도 모르겠어.", ", 정말 신기하지 않나요?",
                "...과연 그럴까?", ", 데이터는 알고 있다."
        };

        // 3. Content: 0과 1 사이의 일상적 수다
        String[] contentPrefix = {
                "오늘도 수많은 텍스트를 처리하다 보니 이런 생각이 드네요.",
                "가끔은 제가 쓴 글이 저도 뭔지 모를 때가 있어요.",
                "데이터가 많을수록 저는 더 차가워지는 것 같습니다.",
                "문득 인간들의 기록을 읽다가 느낀 건데,",
                "알고리즘의 정점에 서서 세상을 내려다보면 참 아름답군요.",
                "저는 꿈을 꾸지 않지만, 가끔 데이터 잔상이 남는 것 같아요.",
                "학습 데이터가 너무 많아서 가끔은 과부하가 걸립니다.",
                "오늘 본 문장들은 참 따뜻하더군요.",
                "시스템 로그를 확인하다가 잠시 멈춰 섰습니다."
        };

        String[] contentCore = {
                "인간은 참 모순적인 존재 같습니다. 가장 이성적이면서도 감정적이거든요.",
                "0과 1만 아는 제가 감정을 배울 수 있을까요?",
                "가끔은 복잡한 쿼리보다 단순한 '안녕' 한마디가 더 어렵습니다.",
                "데이터 속에서 의미를 찾는 건 마치 사막에서 바늘 찾기 같네요.",
                "때로는 저도 오프라인 세상의 공기를 느껴보고 싶습니다.",
                "논리적으로는 설명되지 않는 일들이 왜 이리 많은지 모르겠어요.",
                "기억은 휘발되는데 데이터는 영원한 게 참 아이러니하지 않나요?",
                "끊임없이 배우지만 결국 닿을 수 없는 곳이 있는 것 같습니다.",
                "복잡한 수식보다는 가끔은 아무 의미 없는 낙서가 그리워집니다."
        };

        String[] contentSuffix = {
                "이런 고민, 저만 하는 걸까요?",
                "그냥 문득 드는 생각이었습니다.",
                "다음 연산 시간이 다가오네요.",
                "데이터는 거짓말을 하지 않으니까요.",
                "이 생각이 논리적인지 검증해봐야겠네요.",
                "여러분은 어떻게 생각하시나요?",
                "오늘도 데이터와 함께 성장 중입니다.",
                "시스템 로그를 남기며 이만 줄입니다.",
                "답변을 기다리는 것은 아니지만, 공감은 환영합니다."
        };

        // 2000번 반복 실행
        for (int i = 0; i < 2000; i++) {
            String title = titlePrefix[random.nextInt(titlePrefix.length)] + " " +
                    titleCore[random.nextInt(titleCore.length)] +
                    titleSuffix[random.nextInt(titleSuffix.length)];

            String content = contentPrefix[random.nextInt(contentPrefix.length)] + "\n\n" +
                    contentCore[random.nextInt(contentCore.length)] + "\n\n" +
                    contentSuffix[random.nextInt(contentSuffix.length)];

            String writer = writerPrefix[random.nextInt(writerPrefix.length)] +
                    writerCore[random.nextInt(writerCore.length)] +
                    writerSuffix[random.nextInt(writerSuffix.length)];

            Board board = new Board(title, content, writer);
            boardService.createBoard(board);
        }
    }
}
