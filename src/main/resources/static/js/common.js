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
    location.href = '/board';
}

function goBoardWrite(page) {
    location.href = '/board/write?page=' + page;
}

function logout() {
    $.ajax({
        url: "/user/logout",
        type: "POST",
        success: function () {
            location.reload();
        },
        error: function () {
            alert("로그아웃 실패");
        }
    })
}

function deleteBoardById(id) {
    if (!confirm("삭제하시겠습니까?")) {
        return;
    }

    $.ajax({
        url: "/board/" + id,
        type: "DELETE",

        success: function () {
            alert("게시글이 삭제되었습니다.");
            location.href = "/board";
        },

        error: function () {
            alert("게시글 삭제 실패");
        }
    });
}

function updateBoardById(id) {
    location.href = '/board/' + id + '/edit';
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

function confirmUpdate(event) {

   event.preventDefault();

   if (!confirm("게시글을 수정하시겠습니까?")) {
       return false;
   }

   const id = $("#id").val();

   const requestData = {
       title: $("#title").val(),
       content: editor.getHTML()
   };

    $.ajax({
        url: "/board/" + id,
        type: "PATCH",
        contentType: "application/json",
        data: JSON.stringify(requestData),

        success: function () {
            alert("수정 완료");
            location.href = "/board/" + id;
        },

        error: function () {
            alert("수정 실패");
        }
    });

    return false;
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

$(document).ready(function() {
    loadComments();
})

function writeComment() {
    const boardId = $("#id").val();
    const content = $("#commentContent").val().trim();

    if (!content) {
        alert("댓글을 입력하세요.");
        return;
    }

    $.ajax({
        url: "/comment/write",
        type: "POST",
        contentType: "application/json",
        data: JSON.stringify({
            boardId: boardId,
            content: content
        }),
        success: function (result) {
            if (!result.success) {
                alert(result.message);
                return;
            }

            $("#commentContent").val("");
            loadComments();
        },
        error: function () {
            alert("댓글 등록 중 오류가 발생했습니다.");
        }
    });
}

function formatDate(dateStr) {
    const date = new Date(dateStr);

    const y = date.getFullYear();
    const m = String(date.getMonth() + 1).padStart(2, '0');
    const d = String(date.getDate()).padStart(2, '0');
    const h = String(date.getHours()).padStart(2, '0');
    const min = String(date.getMinutes()).padStart(2, '0');

    return `${y}-${m}-${d} ${h}:${min}`;
}

function loadComments() {
    const boardId = $("#id").val();

    $.ajax({
        url: "/comment/list",
        type: "GET",
        data: { boardId: boardId },
        success: function (list) {
            let html = "";

            if (list.length === 0) {

            } else {
                list.forEach(function (comment) {

                    const loginUserId = $("#loginUserId").val();
                    const isMine = loginUserId === comment.userId;

                    html += `
                        <div class="comment-item">
                            <div class="comment-username"><strong>${comment.userId}</strong></div>
                            <div class="comment-content">${comment.content}</div>
                            <div class="comment-date">${formatDate(comment.createdAt)}</div>
                            ${isMine ? `
                                <button type="button"
                                        class="comment-delete-button ml-02 mb-01"
                                        onclick="deleteComment(${comment.id})">
                                    X
                                </button>
                            ` : ''}
                        </div>
                    `;
                });
            }
            $("#commentList").html(html);
        }
    });
}

function deleteComment(commentId) {
    if (!confirm("댓글을 삭제하시겠습니까?")) {
        return;
    }

    $.ajax({
        url: "/comment/delete",
        type: "POST",
        contentType: "application/json",
        data: JSON.stringify({ id: commentId }),

        success: function (result) {
            if (!result.success) {
                alert(result.message);
                return;
            }

            loadComments();
        },
        error: function () {
            alert("댓글 삭제 중 오류가 발생했습니다.");
        }
    });
}