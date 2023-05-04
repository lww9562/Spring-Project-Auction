document.addEventListener('DOMContentLoaded', function() {
	const toggles = document.querySelectorAll(".form-check-input");
	console.log(toggles);

	for(const el of toggles) {
		el.addEventListener("change", updateProductStats);
	}
});

function updateProductStats(e) {
	const trEl = e.currentTarget.parentElement.parentElement;
	const stats = trEl.querySelector(".form-check-input");
	const productId = stats.dataset.productNo;
    const productStats = stats.dataset.productStats;

	var change_stats = productStats;
	console.log(stats);
	console.log(stats.dataset);
	console.log(stats.value);



	if(e.target.checked){
		//판매 활성화
		change_stats = "true";
	} else {
		//판매 비활성화
		change_stats = "false";
	}
	const params = {productId, productStats};

    const url = `/admin/product?productId=${productId}&stats=${change_stats}`
    commonLib.ajaxLoad(url)
    	.then((data)=> {
    		console.log(data);
    	})
    	.catch((err) => {
    		console.error(err);
    	});
}