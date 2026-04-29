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

    @Test
    void createBoard() {
        Random random = new Random();

        // 1. Writer: 자연스러운 닉네임 조합
        String[] writerPrefix = {"행복", "산책", "커피", "노을", "평온", "열정", "기록", "소소"};
        String[] writerCore = {"러버", "메이트", "조아", "매니아", "로그", "마음", "피플", "다이어리"};
        String[] writerSuffix = {"_01", "77", "님", "이", "로그", "", "", ""};

        // 2. Title: 일상의 조각들
        String[] titlePrefix = {"[일상]", "[주말]", "[오후]", "[생각]", "[발견]", "[감성]", "[기록]", "[추천]"};
        String[] titleCore = {
                "카페에서 보내는 오후", "퇴근길 노을이 정말 예쁘네요", "이번 주말엔 드라이브 갈까",
                "인생은 생각보다 짧고 즐겁네요", "오늘 점심 메뉴는 성공적", "집 앞 공원 산책하다 발견한 것들",
                "바쁜 일상 속 작은 여유", "새로 산 책 읽는 중입니다", "운동 끝나고 마시는 물 한 잔",
                "가끔은 멍하니 있는 시간이 필요해", "오늘 날씨가 딱 외출하기 좋네요"
        };
        String[] titleSuffix = {", 정말 평화롭네요.", ", 다들 좋은 하루 보내세요.", ". 공유합니다.", ". 너무 좋네요.", ", 기분 좋은 하루네요."};

        // 3. Content: 툭 던지는 듯한 일상 이야기
        String[] contentPrefix = {
                "오늘 문득 그런 생각이 들었어요.", "바쁜 하루를 마치고 집에 돌아왔는데,",
                "날씨가 너무 좋아서 그냥 지나칠 수가 없더라고요.", "커피 한 잔 마시면서 창밖을 보는데,",
                "별거 아닌 일상이지만 기록으로 남겨봅니다.", "최근에 가장 즐거웠던 순간은,",
                "여러분은 오늘 어떤 하루를 보내셨나요?", "저는 요즘 이런 소소한 즐거움에 빠져있어요."
        };
        String[] contentCore = {
                "주말에는 아무 생각 없이 푹 쉬는 게 최고인 것 같아요.",
                "좋아하는 음악을 들으면서 걷는 이 길이 너무 좋습니다.",
                "회사 근처 맛집을 발견했는데 여기 정말 괜찮네요.",
                "내일은 또 어떤 일이 기다리고 있을지 기대됩니다.",
                "가끔은 이렇게 멈춰서 하늘을 보는 시간이 꼭 필요한 것 같아요.",
                "오늘 퇴근길에 본 하늘이 너무 예뻐서 사진을 한 장 찍었네요.",
                "다음 주 계획을 짜고 있는데 벌써 설렙니다.",
                "좋은 사람들과 함께하는 저녁은 언제나 옳죠."
        };
        String[] contentSuffix = {
                "\n\n다들 오늘 하루 고생 많으셨습니다. 편안한 밤 되세요!",
                "\n\n이런 평범한 행복이 진짜 소중한 것 같아요.",
                "\n\n다들 오늘 저녁은 맛있는 거 드시고 푹 쉬세요.",
                "\n\n가끔은 이렇게 일상 글을 쓰는 것도 나쁘지 않네요.",
                "\n\n오늘도 고생하셨습니다. 모두 화이팅!",
                "\n\n내일도 무탈하고 행복한 하루 되길 바라요."
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
