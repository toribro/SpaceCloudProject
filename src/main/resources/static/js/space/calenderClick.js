const cUrlStr = window.location.href;


let date = new Date();
let currentDate = null;
document.addEventListener('DOMContentLoaded', function () {

    const url = new URL(cUrlStr);

    const urlParams =url.searchParams
    const spaceNo= urlParams.get('spaceNo');



    let calendarEl = document.getElementById('calendar');
    let calendar = new FullCalendar.Calendar(calendarEl, {
        height: '1000px',
        initialView: 'dayGridMonth',
        aspectRatio: 1.35,
        dateClick: function (info) {
           // alert(info)
            alert(info.dateStr);
            currentDate = info.dateStr;
            console.log(currentDate);
            console.log(typeof (currentDate));
           // console.log(typeof (info));
            fetchBookedTimes(currentDate,spaceNo);
            //disableBookedTimes1(currentDate,[15,16,20,21]);
            
        }


     });


    calendar.render();

     //////필요한 함수


      /// 필요한 로직
            //예약번호와 날짜값 넘겨
            //예약 확인
          
            //날짜 데이터를 서버에보내서 특정날짜의 예약된 시간 값을 가져온다.
            
            function fetchBookedTimes(currentDate,spaceNo){

                $.ajax({
                   // url:'test.sp',
                    url:'time.sp',
                    type:'GET',
                  
                    data:{date:currentDate,
                         spaceNo:spaceNo},

                    success:function(response){
                        //서버에서 시간을 받는다.
                        console.log(response);
                        disableBookedTimes1(currentDate,response)
                    },
                    error:function(error){
                        console.log("error"+error);
                    }


                })


            }
            
            
            
          

            ///제이쿼리로 정리

            function disableBookedTimes1(currentDate,datetimes) {
                let priceColor="rgb(255, 255, 255)"
                let reservatedPriceColor='rgb(128, 128, 128)'
                let selectedColor="rgb(100, 100, 255)"
               
            
                const existTime= [];
                for( let datetime of datetimes){
                    console.log(datetime);
                    existTime.push(datetime['time1'])
                    existTime.push(datetime['time2'])
                }
               


                $("#choiced_date").val(currentDate); // jQuery 사용으로 변경
                $('#time_choice').css("display", "block");
                $('.time_box').each(function(){
                    const time=$(this).find('.time').text();
                   
                    if(existTime.includes(parseInt(time))){
                        $(this).find('.price').css("backgroundColor",reservatedPriceColor);
                        $(this).find('.price').text("마감")
                        $(this).find('.price').attr("disabled",true);
                    }
                    else{
                        $(this).find('.price').css("backgroundColor",priceColor);
                    }
                 })

                let timeArr = []; // 선택된 시간을 저장하는 배열
                let priceValue = 0; // 총 가격을 저장하는 변수
                let count = 0; // 선택된 시간의 개수
            
                $('.time_box').off().on('click', function() {

                    if($(this).find('.price').css("backgroundColor")===reservatedPriceColor){

                        return false;
                    }
                   
                    const time = parseInt($(this).find('.time').text());
                    const price = parseInt($(this).find('.price').text());
                    const isSelected = ($(this).find('.price').css("backgroundColor") === selectedColor);
            



                    if (isSelected) {
                        // 선택 취소 로직
                        $(this).find('.price').css("backgroundColor", priceColor);
                        timeArr = timeArr.filter(t => t !== time); // 시간 배열에서 제거
                        priceValue -= price;
                        count--;
                    } else {
                        if (count >= 2) {
                            alert("최대 2시간까지입니다.")
                            return false;
                        }
                       // 새로운 시간 선택 로직
                        $(this).find('.price').css("backgroundColor", selectedColor);
                        timeArr.push(time);
                        timeArr.sort((a, b) => a - b); // 시간 순서대로 정렬
            
                        if (timeArr.length > 1 && Math.abs(timeArr[timeArr.length - 1] - timeArr[0]) >= 2) {
                            // 연속되지 않는 시간 선택 시 경고
                            alert("연속된 시간이어야 합니다.");
                           $(this).find('.price').css("backgroundColor", priceColor);
                            timeArr.pop(); // 마지막에 추가한 시간 제거
                        } else {
                            priceValue += price;
                            count++;
                        }
                    }
            
                    // 선택된 시간과 총 가격 업데이트
                    $("#choiced_time").val(timeArr.join('시, ') + (timeArr.length > 0 ? '시' : ''));
                    $("#choiced_price").val(priceValue);
                    $("#gap_time").val(count);
                    console.log(timeArr)


                    transmitTime(timeArr);//시간 전송


                 

                });

               
             
            }

    
            function transmitTime(timeArr){
                $('#AddTime').empty();//추가될때마다 비워줘야됨
                for(const t of timeArr){
                  console.log(":"+t)
                  $('#AddTime').append(
                      $('<input>').attr({
                          type:'checkbox',
                          name: 'times[]',
                          value:t,
                          checked:true,
                          hidden:true
                        
                          
                         
                      })
                  )            //.append(' '+t+'<br>')
              }

          }
});






  
            // function disableBookedTimes(currentDate){
            //     // time_choice.style.display = "block";//time_choices보이게

            //      $('#time_choice').css("display","block");
            //     // time_box.style.display='none';
            //     // $('.time_box').css("display","none");



            //      document.querySelector("#choiced_date").value = currentDate;//날짜 넘어감(String)
            
             


            //     let infos = document.querySelectorAll(".time_box")
            //     let choiced_date = document.querySelector("#choiced_date");
            //     let choiced_time = document.querySelector("#choiced_time");
            //     let choiced_price = document.querySelector("#choiced_price");


            //     let count = 0;
            //     let time_arr = [];//입력받은시간
            //     let time_data=[];//입력받은 시간: 예약 시간(1시간,2시간)
            //     let price_value=0;
            //     let check_text=true;
            //     for (let info of infos) {
            //         let time = info.children[0];
            //         let price = info.children[1];

            //         info.onclick = function (e) {

            //             if (count === 2 && price.style.backgroundColor !== 'blue') {
                        

            //                 return false;

            //             }

            //             if (price.style.backgroundColor === 'blue') {
            //                 price.style.backgroundColor = 'yellow';
            //                 // choiced_date.value="";


            //                 //choiced_price.value = "";

            //                 const findIndex = time_arr.indexOf(parseInt(time.innerText));
            //                 if (findIndex > -1) {
            //                     time_arr.splice(findIndex, 1);
            //                 }

                                
            //                 // const findIndex1 = time_arr.indexOf(parseInt(time.innerText)+1);
            //                 // if (findIndex1 > -1) {
            //                 //     time_arr.splice(findIndex1, 2);
            //                 // }

                        
                            
            //                 let temp_str=time_arr


            //                 choiced_time.value = temp_str;
            //                 price_value=price_value-(parseInt(price.innerText));
            //                 choiced_price.value = price_value;

            //                 console.log("클릭취소");
            //                 count--;
            //             }
            //             else {

            //                 console.log(time);
            //                 console.log(time.innerText);
            //                 price.style.backgroundColor = 'blue';
            //                 choiced_date.value = currentDate;


            //                 let temp_time=parseInt(time.innerText);
            //                 time_arr.push(temp_time);
            //             // time_arr.push(temp_time+1)
            //                 if (Math.abs(time_arr[time_arr.length - 1] - time_arr[0]) >= 2) {
            //                     alert("연속된 시간이어야합니다.");
            //                     price.style.backgroundColor = 'yellow';
            //                     time_arr.pop();
            //                     check_text=false;

            //                 }
            //                 else {
            //                     time_arr.sort((a, b) => a - b);//오름차순 정렬
                                
            //                     let temp_str=time_arr.join('시,')
            //                     choiced_time.value = temp_str;
            //                     price_value=price_value+(parseInt(price.innerText));

            //                     choiced_price.value = price_value;
            //                     count++;

            //                 }


            //             }
            //             console.log(count)
            //             console.log(time_arr)
            //             document.querySelector("#gap_time").value=count;

            //             if(choiced_time.value!==""&&check_text===true){
            //                 choiced_time.value+='시'
            //             }

            //             check_text=true;
            //             console.log(time_arr);
            //         }
            //     }
             

            // }