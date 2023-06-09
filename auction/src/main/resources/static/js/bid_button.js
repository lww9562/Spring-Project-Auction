
window.addEventListener("DOMContentLoaded", function() {
	const buttonEl = document.getElementById("bid_btn");
	const csrfEl = document.querySelector("meta[name='_csrf']");
	const csrfHeaderEl = document.querySelector("meta[name='_csrf_header']");
	console.dir(buttonEl);

	buttonEl.addEventListener("click", async function() {
		var endprice = this.dataset.endprice;
		console.log(endprice);
		if (!confirm("현재 입찰가는 " + endprice +"원 입니다. 정말 입찰하시겠습니까?")) {
    		return;
    	}
		try{
		    const dataset = this.dataset;

		    const productId = dataset.productid;
		    const userId = dataset.userid;

			const data = await axios({
				method: "POST",
				url : "/product/bid",
				data : {
					userId : userId,
					productId : productId
				},
				headers : {
                            		[csrfHeaderEl.content] : csrfEl.content,
                            		"content-type" : "application/x-www-form-urlencoded"
                            	}
			});	// 반환값 : resolve의 매개변수 값
			console.log(data);
            location.reload();
		} catch(err) {
			alert(err.response.data.message);
		}
	});
}, false);