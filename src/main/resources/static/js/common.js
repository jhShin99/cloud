$(function () {
    $('.nav-item').on('click', function () {
        $('.nav-item').removeClass('active'); // 기존 선택 제거
        $(this).addClass('active');           // 클릭한 것만 유지
    })
})

function goMain() {
    location.href = '/';
}

function goBoardList() {
    location.href = '/board/list';
}

function goBoardWrite() {
    location.href = '/board/write';
}

function undoBoardWrite() {
    location.href = '/board/list';
}

function logout() {
    location.href = '/user/logout';
}

function deleteBoardById(id) {
    if (confirm("삭제하시겠습니까?")) {
        location.href = '/board/delete/' + id;
    }
}

function updateBoardById(id) {
    location.href = '/board/update/' + id;
}

function undoUpdate(id) {
    location.href = '/board/read/' + id;
}

function confirmWrite() {

    if (confirm("게시글을 등록하시겠습니까?")) {
        // 에디터 내용 가져오기
        const content = editor.getHTML();

        // hidden input에 넣기
        document.getElementById('content').value = content;
        return true;
    }
    return false;
}

function confirmUpdate() {
    const content = editor.getHTML();
    document.getElementById('content').value = content;
    return confirm("게시글을 수정하시겠습니까?");
}

function toggleLike() {
    const boardId = $("#id").val();

    $.ajax({
        url: "/board/like",
        type: "POST",
        contentType: "application/json",
        data: JSON.stringify({ boardId: boardId }),

        success: function (result) {
            if (!result.success) {
                alert(result.message);
                return;
            }

            $(".likeCount").text(result.likeCount);
            $("#likeButton").toggleClass("liked");
        },

        error: function () {
            alert("요청 중 오류가 발생했습니다.");
        }
    });
}