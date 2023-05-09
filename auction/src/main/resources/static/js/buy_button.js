window.addEventListener("DOMContentLoaded", function() {
	const buttonEl = document.getElementById("buy_btn");
	const csrfEl = document.querySelector("meta[name='_csrf']");
	const csrfHeaderEl = document.querySelector("meta[name='_csrf_header']");
	console.dir(buttonEl);

	buttonEl.addEventListener("click", async function() {
		if (!confirm("정말 구매하시겠습니까?")) {
        		return;
        	}
		try{
		    const dataset = this.dataset;

		    const productId = dataset.productid;
		    const userId = dataset.userid;

			const data = await axios({
				method: "POST",
				url : "/product/buy",
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
            location.href = getContextPath()+"/list";
		} catch(err) {
			console.log(err);
			alert(err.response.data.message);
		}
	});
}, false);

function getContextPath() {
	var hostIndex = location.href.indexOf( location.host ) + location.host.length;
	return location.href.substring( hostIndex, location.href.indexOf('/', hostIndex + 1) );
}