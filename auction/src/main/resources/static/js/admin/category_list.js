document.addEventListener('DOMContentLoaded', function() {
	const toggles = document.querySelectorAll(".form-check-input");
	console.log(toggles);

	for(const el of toggles) {
		el.addEventListener("change", updateCategoryStats);
	}
});

function updateCategoryStats(e) {
	const trEl = e.currentTarget.parentElement.parentElement;
	const stats = trEl.querySelector(".form-check-input");
	const cateID = stats.dataset.cateid;
    const use = stats.dataset.use;

	var change_stats = use;

	if(e.target.checked){
		//판매 활성화
		change_stats = "true";
	} else {
		//판매 비활성화
		change_stats = "false";
	}
	const params = {cateID, change_stats};

    const url = `/admin/category/update?cateID=${cateID}&stats=${change_stats}`
    commonLib.ajaxLoad(url)
    	.then((data)=> {
    		console.log(data);
    	})
    	.catch((err) => {
    		console.error(err);
    	});
}