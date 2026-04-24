$(function () {
    $('.nav-item').on('click', function () {
        $('.nav-item').removeClass('active'); // 기존 선택 제거
        $(this).addClass('active');           // 클릭한 것만 유지
    })
})

function goMain() {
    location.href = '/';
}