# Spring Boot를 활용한 프로젝트
## 중고 물품 경매 사이트
### 마인드맵 & 사이트맵

![mindmap](https://user-images.githubusercontent.com/50220165/233423287-e0f01ace-6804-47ea-a2ac-0252a06de5fa.png)
![sitemap](https://user-images.githubusercontent.com/50220165/233424006-7df2bb66-c1b5-4f4e-856e-5fd7cc1b3495.png)

</br></br></br>
### [경매의 시작/진행]
* 판매자
  * 판매자는 즉시 구매 가격과 증가할 입찰액을 기재하여, 물품을 등록한다.
  * 판매 물품 등록 페이지에는 사진을 포함한 물건에 대한 설명이 포함된다.
  * 판매 물품을 등록할 때에는 특정 수수료가 존재한다.
  
</br>

* 구매자
  * 입찰자는 원하는 물품에 입찰할 수 있다.
  * 이 때, 구매자별로 개인의 보증금을 넣어두고, 입찰액이 보증금보다 적을 때에만 입찰이 가능하도록 한다.
  * 입찰 즉시 입찰액은 보증금에서 빠져나가며, 상회 입찰자가 나타날 경우, 
  입찰액은 다시 보증금으로 환급된다.


### [경매의 종료]
* 입찰액이 즉시 구매 가격에 이르면, 경매는 자동으로 종료된다.
* 또한, 지정된 경매 기간이 경과하면, 경매는 자동으로 종료된다.
  * 입찰자가 존재하지 않는 경우
    * 경매는 유찰된다.
  * 입찰자가 존재하는 경우
    * 최고액으로 입찰한 입찰자와의 거래가 이루어진다.
    * 또한, n%의 수수료를 중개사이트에 지불한다.


</br></br></br> 
![화면구현 설계서-06](https://github.com/lww9562/Spring-Project-Auction/assets/50220165/9f77a0b9-f6ac-4567-a936-27ef167f0431)
![화면구현 설계서-07](https://github.com/lww9562/Spring-Project-Auction/assets/50220165/25ba4b74-eef7-45ea-9a1e-c7d78c9696ce)
![화면구현 설계서-08](https://github.com/lww9562/Spring-Project-Auction/assets/50220165/cb51e50f-0a0a-4152-94da-1fbbfe792366)
![화면구현 설계서-09](https://github.com/lww9562/Spring-Project-Auction/assets/50220165/8d9d711d-a736-4c10-be82-75772df61564)
![화면구현 설계서-10](https://github.com/lww9562/Spring-Project-Auction/assets/50220165/4af07a2f-0dab-40e6-b624-bbe7a4fd0cb0)
![화면구현 설계서-11](https://github.com/lww9562/Spring-Project-Auction/assets/50220165/ea85dbcc-20ae-4729-8410-a8a35436a59c)
![화면구현 설계서-12](https://github.com/lww9562/Spring-Project-Auction/assets/50220165/a1955ff1-e5a0-41f8-9cee-f118f3eed1cd)
![화면구현 설계서-13](https://github.com/lww9562/Spring-Project-Auction/assets/50220165/368c1597-12c2-4c34-b874-72b8659a2881)
![화면구현 설계서-14](https://github.com/lww9562/Spring-Project-Auction/assets/50220165/26f4cbbc-f63a-423d-9956-996e5cdfa78b)
![화면구현 설계서-15](https://github.com/lww9562/Spring-Project-Auction/assets/50220165/9a568ef1-7333-4d42-a16c-b5f45ac91fc5)
![화면구현 설계서-16](https://github.com/lww9562/Spring-Project-Auction/assets/50220165/48e36564-2949-42ac-b2d0-27af68813e26)
![화면구현 설계서-17](https://github.com/lww9562/Spring-Project-Auction/assets/50220165/58d88ac3-0ed8-4af2-8a1e-ef4711170ac0)
![화면구현 설계서-18](https://github.com/lww9562/Spring-Project-Auction/assets/50220165/ef84b663-12ae-43a5-a557-ff377f7d5624)
![화면구현 설계서-19](https://github.com/lww9562/Spring-Project-Auction/assets/50220165/2956a324-257e-404c-964b-84798b883d68)
![화면구현 설계서-20](https://github.com/lww9562/Spring-Project-Auction/assets/50220165/903709dd-3c7e-4fef-8ee3-913415196464)
![화면구현 설계서-21](https://github.com/lww9562/Spring-Project-Auction/assets/50220165/6e49e40c-79ca-461f-999e-67e32f135780)
![화면구현 설계서-22](https://github.com/lww9562/Spring-Project-Auction/assets/50220165/7cfb3952-ee14-44be-9c72-53058ffbbcb2)
![화면구현 설계서-23](https://github.com/lww9562/Spring-Project-Auction/assets/50220165/1901b03b-1937-4ef1-9345-a3ba2c731ee8)
![화면구현 설계서-24](https://github.com/lww9562/Spring-Project-Auction/assets/50220165/994a43ef-2073-4023-86bc-701f3c827444)
