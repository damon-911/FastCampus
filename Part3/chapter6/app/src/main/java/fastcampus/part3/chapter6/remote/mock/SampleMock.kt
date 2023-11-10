package fastcampus.part3.chapter6.remote.mock

import fastcampus.part3.chapter6.model.*

object SampleMock {

    fun mockChapter6List(): List<ListItem> {
        val list = mutableListOf<ListItem>().apply {
            add(mockViewPager())
            add(mockFullAd())
            add(mockHorizontal2())
            add(mockHorizontal1())
            add(
                FullAd(
                    "동네마트 장보기",
                    "https://cdn.pixabay.com/photo/2023/04/04/15/06/children-7899562_1280.jpg",
                    "구매하러 가기"
                )
            )
            add(mockCoupon())
            add(mockHorizontal2())
            add(mockHorizontal3())
            add(mockHorizontal4())
            add(
                FullAd(
                    "블랙프라이데이! 50% 할인",
                    "https://cdn.pixabay.com/photo/2015/01/08/18/24/children-593313_1280.jpg",
                    "구매하러 가기"
                )
            )

            add(mockViewPager())
            add(mockHorizontal4())
            add(mockCoupon())
            add(mockHorizontal1())
            add(mockHorizontal2())

        }

        return list
    }

    private fun mockViewPager() = ViewPager(mutableListOf<ListItem>().apply {
        add(Image("https://cdn.pixabay.com/photo/2023/04/28/09/59/mower-7956264_1280.jpg"))
        add(Image("https://cdn.pixabay.com/photo/2014/11/16/15/15/field-533541_1280.jpg"))
        add(Image("https://cdn.pixabay.com/photo/2015/04/23/22/00/tree-736885_1280.jpg"))
        add(Image("https://cdn.pixabay.com/photo/2014/04/14/20/11/pink-324175_1280.jpg"))
    })

    private fun mockFullAd() = FullAd(
        "장보기 위크",
        "https://cdn.pixabay.com/photo/2014/11/16/15/15/field-533541_1280.jpg"
    )

    private fun mockCoupon() = Horizontal(title = "오늘의 쿠폰",
        items = mutableListOf<ListItem>().apply {
            add(
                Coupon(
                    "https://img.freepik.com/free-photo/mega-sale-for-retail-with-pink-circle_23-2149656610.jpg?size=626&ext=jpg&ga=GA1.2.1575512813.1668176396&semt=sph",
                    "메가 세일! 전 상품 할인 중!",
                    "~20% 쿠폰"
                )
            )
            add(
                Coupon(
                    "https://img.freepik.com/free-vector/seasonal-sale-discounts-presents-purchase-visiting-boutiques-luxury-shopping-price-reduction-promotional-coupons-special-holiday-offers-vector-isolated-concept-metaphor-illustration_335657-2766.jpg?size=338&ext=jpg&ga=GA1.2.1575512813.1668176396",
                    "장바구니 쿠폰! 최대 40%",
                    "~40% 쿠폰"
                )
            )
            add(
                Coupon(
                    "https://img.freepik.com/free-vector/online-shopping-concept-illustration_114360-1084.jpg?size=626&ext=jpg&ga=GA1.2.1575512813.1668176396",
                    "의류 쿠폰! 겨울 코트 장만하세요",
                    "~84% 쿠폰"
                )
            )
            add(
                Coupon(
                    "https://img.freepik.com/free-vector/black-friday-concept-illustration_114360-3667.jpg?size=338&ext=jpg&ga=GA1.2.1575512813.1668176396",
                    "블랙 프라이데이! 두번 다시 없을 할인 쿠폰",
                    "~77% 쿠폰"
                )
            )
            add(
                Coupon(
                    "https://img.freepik.com/free-photo/basket-full-of-vegetables_1112-316.jpg?size=626&ext=jpg&ga=GA1.2.1575512813.1668176396",
                    "제철 과일 할인 쿠폰!",
                    "~57% 쿠폰"
                )
            )

        }
    )

    private fun mockHorizontal1() = Horizontal(title = "이 주의 쇼핑 특가",
        items = mutableListOf<ListItem>().apply {
            add(
                SellItem(
                    "https://images.freeimages.com/vhq/images/previews/fb1/price-tag-psd-templates-394247.jpg",
                    "다엑스 광시야각 70cm 24인치 75Hz 게이밍 컴퓨터 모니터",
                    102000,
                    "빠른 배송",
                    4.5F
                )
            )

            add(
                SellItem(
                    "https://images.freeimages.com/vhq/images/previews/4e4/versatile-bottle-mockup-423324.jpg",
                    "엑스코리아 30인치 FHD TV 모니터겸용",
                    149000,
                    "무료 배송",
                    4.5F
                )
            )
            add(
                SellItem(
                    "https://images.freeimages.com/images/previews/233/buddah-statue-in-hong-kong-1222149.jpg",
                    "루나립 듀얼 모니터 암, 블랙, 1개",
                    93000,
                    "빠른 배송",
                    null
                )
            )
            add(
                SellItem(
                    "https://images.freeimages.com/clg/images/80/80273/beach-4_p.jpg",
                    "클라유즈 프리미엄 패널 24인치 모니터 컴퓨터 게이밍 사무용 화이트",
                    140000,
                    "무료 배송",
                    4.5F
                )
            )
            add(
                SellItem(
                    "https://images.freeimages.com/variants/8teRfR1XVfbwzmP8afZJiykQ/f4a36f6589a0e50e702740b15352bc00e4bfaf6f58bd4db850e167794d05993d",
                    "와이어뷰 60cm FHD 내장스피커 모니터",
                    129000,
                    "당일 배송",
                    5F
                )
            )
            add(
                SellItem(
                    "https://images.freeimages.com/images/previews/ac9/railway-hdr-1361893.jpg",
                    "모니터 편광필름 프라이버시 스크린 필터 25 26 27 28인치 16:9 16:10",
                    27000,
                    null,
                    null
                )
            )

            add(
                SellItem(
                    "https://images.freeimages.com/images/previews/cd0/underground-london-1532321.jpg",
                    "디에스요이 81cm FHD 모니터",
                    173000,
                    "무료 배송",
                    3.5F
                )
            )

            add(
                SellItem(
                    "https://images.freeimages.com/images/previews/101/red-lady-1241907.jpg",
                    "32인치 중소기업 TV 겸용 모니터 TV",
                    101320,
                    null,
                    4.5F
                )
            )

            add(
                SellItem(
                    "https://images.freeimages.com/images/previews/4db/bottle-1546720.jpg",
                    "78.58cm FHD 베젤리스 게이밍 모니터 75Hz",
                    141550,
                    "빠른 배송",
                    5F
                )
            )

            add(
                SellItem(
                    "https://images.freeimages.com/images/previews/56f/butterfly-on-daisy-1560505.jpg",
                    "LG IPS패널 68.6cm 75Hz 27형 사무용 게이밍 컴퓨터 모니터",
                    134000,
                    null,
                    4.5F
                )
            )
        })


    private fun mockHorizontal2() = Horizontal(title = "이 상품은 필수로 사야해!",
        items = mutableListOf<ListItem>().apply {
            add(
                Sale(
                    "https://img.freepik.com/free-photo/young-adult-organizing-documents_23-2149396695.jpg?size=626&ext=jpg&ga=GA1.2.1575512813.1668176396&semt=sph",
                    "지금 15% 할인 중!",
                    "코끼리 파일집",
                    "무료배송"
                )
            )

            add(
                Sale(
                    "https://img.freepik.com/free-photo/organized-cabinet-at-home_23-2148857501.jpg?size=626&ext=jpg&ga=GA1.2.1575512813.1668176396&semt=sph",
                    "지금 25% 할인 중!",
                    "나리 서랍 정리함",
                    null
                )
            )
            add(
                Sale(
                    "https://img.freepik.com/free-photo/close-up-classify-male-underwear_23-2148857515.jpg?size=626&ext=jpg&ga=GA1.2.1575512813.1668176396&semt=sph",
                    "지금 29% 할인 중!",
                    "더있소 킹콩 속옷 정리함",
                    "무료배송"
                )
            )
            add(
                Sale(
                    "https://images.freeimages.com/images/previews/46e/red-beetle-1416148.jpg",
                    "지금 10% 할인 중!",
                    "코쿠리 보조배터리 흡착패드 화이트",
                    "무료배송"
                )
            )
            add(
                Sale(
                    "https://images.freeimages.com/images/previews/5a4/mail-box-1422055.jpg",
                    "지금 33% 할인 중!",
                    "소파 무선충전기 거치대 갤럭시 아이폰 디즈니에디션",
                    null
                )
            )
            add(
                Sale(
                    "https://images.freeimages.com/variants/htJPgwZuxUMTq4Vn3NDQ5THz/f4a36f6589a0e50e702740b15352bc00e4bfaf6f58bd4db850e167794d05993d",
                    "지금 52% 할인 중!",
                    "15인치 초슬림 DEX 포터블 HDR 휴대용 모니터",
                    "무료배송"
                )
            )
            add(
                Sale(
                    "https://images.freeimages.com/images/previews/802/kaaba-mecca-city-center-saudiarb-muslims-masjid-haram-mosque-prayer-1441239.jpg",
                    "지금 15% 할인 중!",
                    "청승테크 3in1 고속 무선충전기 거치대 삼성 갤럭시 아이폰 애플워치 갤럭시워치 QC3.0",
                )
            )
            add(
                Sale(
                    "https://images.freeimages.com/variants/N2q96guqeZAWUYwFTZWvi1WC/f4a36f6589a0e50e702740b15352bc00e4bfaf6f58bd4db850e167794d05993d",
                    "지금 60% 할인 중!",
                    "자동 출 전기 포트 온도 조절 티 메이커 보온 분유 급 수 무선",
                    "무료배송"
                )
            )
            add(
                Sale(
                    "https://images.freeimages.com/images/previews/4fe/wiene-museum-1217586.jpg",
                    "지금 72% 할인 중!",
                    "라면3개도 거뜬! 원터치 멀티 라면포트 1.8L+계란찜판 증정",
                )
            )
            add(
                Sale(
                    "https://images.freeimages.com/images/previews/e56/run-away-1555225.jpg",
                    "지금 15% 할인 중!",
                    "델가체 기본형 스탠드 선풍기 DXF-2260",
                )
            )
            add(
                Sale(
                    "https://images.freeimages.com/images/previews/f28/silver-laptop-1-1616821.jpg",
                    "지금 48% 할인 중!",
                    "델로이 충전식 캠핑용 선풍기 타프팬",
                    "무료배송"
                )
            )
        })

    private fun mockHorizontal3() = Horizontal(title = "지금 이 상품이 필요하신가요?",
        items = mutableListOf<ListItem>().apply {
            add(
                Sale(
                    "https://images.freeimages.com/images/previews/d41/bear-combat-2-1332988.jpg",
                    "지금 5% 할인 중!",
                    "필 C1 듀얼포트 어댑터 12W",
                    "무료배송"
                )
            )

            add(
                Sale(
                    "https://images.freeimages.com/images/previews/af4/sparklers-2-1200038.jpg",
                    "지금 15% 할인 중!",
                    "더블더 어댑터 퀄컴 퀵차지 고속충전기 18W",
                    null
                )
            )
            add(
                Sale(
                    "https://media.istockphoto.com/id/1370883151/photo/mothers-day-card-with-pink-flowers-and-heart.jpg?b=1&s=612x612&w=0&k=20&c=Gf5iMCww9kcJU1jR0mP_NtUV-twwzV_rWjs9djw21Ps=",
                    "지금 19% 할인 중!",
                    "스서펄 GaQ89S USB-PD 고속 3포트 멀티충전기 어댑터 65W FT-3U30X",
                    "무료배송"
                )
            )
            add(
                Sale(
                    "https://media.istockphoto.com/id/1444397170/photo/california-poppy-orange-flower.jpg?b=1&s=612x612&w=0&k=20&c=MZ2CCXKgM-vusZ2dhz8N4HTCRVyMbCzEfZ5mIoNzmC4=",
                    "지금 40% 할인 중!",
                    "스서펄 C4 4포트 멀티충전기 어댑터 + A to C 충전 케이블",
                    "무료배송"
                )
            )
            add(
                Sale(
                    "https://images.freeimages.com/variants/ySo9jhnWR2WTs2H4uTahmgR2/f4a36f6589a0e50e702740b15352bc00e4bfaf6f58bd4db850e167794d05993d",
                    "지금 33% 할인 중!",
                    "내시오 C3 18W 3포트 USB 어댑터 멀티 충전기",
                    null
                )
            )
            add(
                Sale(
                    "https://media.istockphoto.com/id/1189304032/ko/%EC%82%AC%EC%A7%84/%ED%9A%8C%EC%9D%98%EC%8B%A4%EC%97%90%EC%84%9C-%EB%94%94%EC%A7%80%ED%84%B8-%ED%83%9C%EB%B8%94%EB%A6%BF%EC%9D%84-%EB%93%A4%EA%B3%A0-%EC%9E%88%EB%8A%94-%EC%9D%98%EC%82%AC.jpg?s=612x612&w=0&k=20&c=6MmJZYDmpZLdZBH3vsV0C2dNg5M7DFpaKd7bpRzlOH4=",
                    "지금 52% 할인 중!",
                    "비바필 GaQ89S USB-PD 고속 3포트 멀티충전기 어댑터 65W",
                    "무료배송"
                )
            )
            add(
                Sale(
                    "https://images.freeimages.com/variants/LEuv6yp1xJZbJZEcKha8nnmH/f4a36f6589a0e50e702740b15352bc00e4bfaf6f58bd4db850e167794d05993d",
                    "지금 15% 할인 중!",
                    "카이누 C63 18W 3포트 USB 어댑터 멀티 충전기 + A to C 케이블",
                )
            )
            add(
                Sale(
                    "https://images.freeimages.com/images/previews/804/tubes-1252172.jpg",
                    "지금 60% 할인 중!",
                    "삼다요 A2S 36W USB 듀얼 고속충전기 AC3.0 + C타입 고속충전 케이블",
                    "무료배송"
                )
            )
            add(
                Sale(
                    "https://images.freeimages.com/images/previews/f18/natalie-1-1434418.jpg",
                    "지금 72% 할인 중!",
                    "고속충전기 30W 3포트 USB 급속충전기 아답터",
                )
            )
            add(
                Sale(
                    "https://media.istockphoto.com/id/951514270/ko/%EC%82%AC%EC%A7%84/%EB%8B%B9%EC%8B%A0%EA%B3%BC-%ED%95%A8%EA%BB%98-%EC%9E%91%EB%8F%99-%ED%95%98%EB%8F%84%EB%A1%9D-%EB%8B%A4%ED%96%89.jpg?s=612x612&w=0&k=20&c=jv1TBUPFC7HIOtG_vtXGei9c4mMlwbYEispal2EeiLs=",
                    "지금 15% 할인 중!",
                    "더더 듀얼 아이패드 아이폰 고속 충전기 12W",
                )
            )
            add(
                Sale(
                    "https://images.freeimages.com/images/previews/934/antelope-canyon-6-1532993.jpg",
                    "지금 48% 할인 중!",
                    "알로에 멀티충전기 스마트IC 5포트 UC23D01",
                    "무료배송"
                )
            )
        })

    private fun mockHorizontal4() = Horizontal(title = "최근 보던 상품의 연관 상품",
        items = mutableListOf<ListItem>().apply {
            add(
                SellItem(
                    "https://images.freeimages.com/images/large-previews/bb0/cat-in-window-1218032.jpg",
                    "인메이드 스테인레스 음식물 쓰레기통",
                    38800,
                    "당일 배송",
                    4.5F
                )
            )

            add(
                SellItem(
                    "https://images.freeimages.com/images/previews/042/costarricense-1639814.png",
                    "모스부호 프리미엄 니트릴 고무장갑 3개입",
                    119000,
                    "빠른 배송",
                    4.5F
                )
            )
            add(
                SellItem(
                    "https://images.freeimages.com/images/previews/172/old-tree-1622803.jpg",
                    "지카큐 100억 생유산균 골드",
                    26240,
                    null,
                    5F
                )
            )
            add(
                SellItem(
                    "https://images.freeimages.com/images/previews/712/red-church-1641745.jpg",
                    "일품 황제 침침환 침향23% 총60환 쇼핑백포함 녹용 홍삼 산삼 침향 명절 부모님 효도 선물 선물세트",
                    93000,
                    null,
                    null
                )
            )
            add(
                SellItem(
                    "https://images.freeimages.com/images/previews/a11/windsor-canada-panorama-1446829.jpg",
                    "크리스탈 남아 여아 주니어 스쿨룩 V넥 니트 조끼",
                    45000,
                    null,
                    4.5F
                )
            )
            add(
                SellItem(
                    "https://images.freeimages.com/images/previews/c34/dog-1392233.jpg",
                    "오트펄 리본토끼 레깅스/토끼레깅스/여아레깅스",
                    22000,
                    null,
                    5F
                )
            )
            add(
                SellItem(
                    "https://media.istockphoto.com/id/1369652228/photo/family-with-two-kids-on-their-holidays-in-california-near-the-ocean.jpg?b=1&s=612x612&w=0&k=20&c=13OaUlGCX2jEdBd1G6vFoi9C3_Y-hSpGhg0SW1FP7Ls=",
                    "오토오토 A41 블랙다이아몬드 썬바이져 더뉴 K0 2세대 (2015-2019) 6PCS 썬바이저 도어바이저",
                    37000,
                    "당일 배송",
                    4F
                )
            )

            add(
                SellItem(
                    "https://images.freeimages.com/images/previews/071/spring-1373199.jpg",
                    "위다모 24V LED 차폭등 사이드램프 대형 화물차차폭등 화물차용품 24v led 시그널램프 화물차작업등",
                    45000,
                    "빠른 배송",
                    5F
                )
            )

            add(
                SellItem(
                    "https://images.freeimages.com/images/previews/ed2/skydiving-1315361.jpg",
                    "자동차터보사운드 휘슬 휘파람 머플러 커스텀 튜닝",
                    5500,
                    null,
                    4F
                )
            )

            add(
                SellItem(
                    "https://images.freeimages.com/images/previews/d8a/perfect-blue-buildings-2-1235862.jpg",
                    "인기 유리한 플레이트",
                    15500,
                    "무료 배송",
                    5F
                )
            )

            add(
                SellItem(
                    "https://images.freeimages.com/images/previews/04e/sunset-1189623.jpg",
                    "LG IPS패널 68.6cm 75Hz 27형 사무용 게이밍 컴퓨터 모니터",
                    134000,
                    null,
                    4.5F
                )
            )
        })
}