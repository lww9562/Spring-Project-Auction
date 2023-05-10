window.addEventListener("DOMContentLoaded", function() {
	/** 변경 버튼 클릭시 사용자 적립금 변경 처리 S */
	const modifyBtns = document.querySelectorAll(".money_list .modify");

	for (const el of modifyBtns) {
		el.addEventListener("click", updateUserMoney);
	}

	/** 변경 버튼 클릭시 사용자 적립금 변경 처리 E */
});
function updateUserMoney(e) {
	if (!confirm("정말 변경하시겠습니까?")) {
		return;
	}

	const trEl = e.currentTarget.parentElement.parentElement;
    const moneyInput = trEl.querySelector(".money");

    const money = Number(moneyInput.value);
    const userId = moneyInput.dataset.userId;
    const id = Number(moneyInput.id);
    console.log(money);
    console.log(userId);
    console.log(id);
    const params = { userId, money, id };

	const url = `/admin/money/money?userId=${userId}&money=${money}&id=${id}`;
	commonLib.ajaxLoad(url)
		.then((data) => {
			console.log(data);
			location.reload();
		})
		.catch((err) => {
			console.error(err);
		});

}