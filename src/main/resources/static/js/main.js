document.getElementById('map-btn').addEventListener('click', function () {
    var mapContainer = document.getElementById('map'),
        mapOption = {
            center: new kakao.maps.LatLng(37.50089653784943, 127.02815682483896),
            level: 3
        };

    var map = new kakao.maps.Map(mapContainer, mapOption);
    var geocoder = new daum.maps.services.Geocoder();

    let listData = /*[[${spList}]]*/ [];
    let listSpName = /*[[${spList}]]*/ [];

    listData.forEach(function (addr, index) {
        geocoder.addressSearch(addr, function (result, status) {
            if (status === daum.maps.services.Status.OK) {
                var coords = new daum.maps.LatLng(result[0].y, result[0].x);
                var marker = new daum.maps.Marker({
                    map: map,
                    position: coords
                });
                var infowindow = new daum.maps.InfoWindow({
                    content: '<div style="width:150px;text-align:center;">' + listSpName[index] + '</div>',
                    disableAutoPan: true
                });
                infowindow.open(map, marker);
            }
        });
    });

    setTimeout(function () {
        map.relayout();
    }, 100);
});

function detailView(spaceNo) {
    location.href = 'space/'+spaceNo;
}
