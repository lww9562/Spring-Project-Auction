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
    const moneyInput = trEl.querySelector(".money1");

    const money = Number(moneyInput.value);
    const userId = moneyInput.dataset.userId;

    const params = { userId, money };

	const url = `/admin/money/money?userId=${userId}&money=${money}`;
	commonLib.ajaxLoad(url)
		.then((data) => {
			console.log(data);
		})
		.catch((err) => {
			console.error(err);
		});

}