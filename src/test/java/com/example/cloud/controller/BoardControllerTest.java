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

        // 1. Writer: 고닉과 유동닉의 적절한 조화
        String[] writerPrefix = {"해축", "국야", "GOAT", "메시", "황희찬", "류현진", "무관", "킹", "갓"};
        String[] writerCore = {"박사", "전문가", "팬", "탈출지능", "수호자", "버스터", "헌터", "빌런"};
        String[] writerSuffix = {"", "", "", "ㅇㅇ", "ㅇㅇ(121.162)", "ㅇㅇ(211.234)", "ㅇㅇ(118.235)"};

        // 2. Title: 자극적이고 짧은 커뮤니티식 제목
        String[] titlePrefix = {
                "[오피셜]", "오늘자", "속보)", "근데", "솔직히", "진짜", "아니", "와", "레전드", "이거봐라", "강추)"
        };

        String[] titleCore = {
                "메시 폼 미쳤다", "해리 케인 또 무관 확정", "손흥민 푸스카스급 골", "심판 판정 이게 맞냐",
                "올해 우승팀 미리 정해줌", "이 형님은 늙지도 않네", "월클 기준 정리해드림", "방금 터진 대박 수비",
                "감독 경질설 떴다", "유망주 한 명 발견함", "야구는 9회말 2아웃부터지", "오늘 경기 직관 후기",
                "이 팀은 미래가 없다", "연봉값 못하는 선수 TOP 3", "역대급 오심 등장"
        };

        String[] titleSuffix = {
                "ㅋㅋㅋ", "ㄷㄷㄷ", "ㅠㅠ", "??", " 실화냐?", " 인가요..", " 개추 ㅋㅋ", " 대박이네", " 미쳤음"
        };

        // 3. Content: 짧고 강렬한 본문 + 짤방 언급
        String[] contentPrefix = {
                "방금 봤냐?", "진짜 소름 돋았다..", "이게 선수냐?", "아니 여기서 이걸?",
                "팩트만 말함.", "아무도 반박 못 할 듯.", "오늘 경기 요약해준다.", "형들 의견 궁금함.",
                "진짜 화가 나서 못 보겠다.", "이 맛에 스포츠 본다."
        };

        String[] contentCore = {
                "전성기 시절 폼 돌아왔네 ㄷㄷㄷ", "진짜 팀 세대교체 시급하다.", "돈 받고 뛰는 거 맞냐? 내가 뛰어도 저거보단 잘함.",
                "기본기가 아예 안 되어있는데 어떻게 프로지?", "오늘 심판 돈 얼마 받았냐? 눈이 있으면 저걸 안 불어?",
                "진짜 하늘이 돕는다 ㅋㅋㅋ 이게 들어가네.", "감독 전술 실화냐? 교체 카드 쓰는 꼴 좀 봐라.",
                "역대급 커리어 하이 찍을 듯.", "이 팀 응원하는 내가 레전드다.", "깔 수가 없다 진짜 완벽했음."
        };

        String[] contentSuffix = {
                "\n\n반박 시 니 말이 맞음.", "\n\nㄹㅇㅋㅋ", "\n\n내일 뉴스 도배되겠네.", "\n\n추천 좀 눌러줘라.",
                "\n\n후... 일단 술 마시러 간다.", "\n\n인정하면 개추 좀 ㅋㅋㅋ", "\n\n앞으로 이 팀 경기는 안 본다.",
                "\n\n성지순례 미리 와라."
        };

        for (int i = 0; i < 2000; i++) {
            // 닉네임 생성 (유동닉 확률 추가)
            String writer;
            if (random.nextInt(10) < 4) { // 40% 확률로 유동닉
                writer = writerSuffix[random.nextInt(4) + 3];
            } else {
                writer = writerPrefix[random.nextInt(writerPrefix.length)] +
                        writerCore[random.nextInt(writerCore.length)] +
                        writerSuffix[random.nextInt(3)];
            }

            String title = (
                    titlePrefix[random.nextInt(titlePrefix.length)] + " " +
                            titleCore[random.nextInt(titleCore.length)] + " " +
                            titleSuffix[random.nextInt(titleSuffix.length)]
            ).trim();

            String content =
                    contentPrefix[random.nextInt(contentPrefix.length)] + "\n\n" +
                            contentCore[random.nextInt(contentCore.length)] + "\n\n" +
                            contentSuffix[random.nextInt(contentSuffix.length)];

            Board board = new Board(title, content, writer);
            boardService.createBoard(board);
        }
    }
}
