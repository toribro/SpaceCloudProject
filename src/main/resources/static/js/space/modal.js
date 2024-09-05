     
                                    document.addEventListener('DOMContentLoaded', function () {
                                      

                                        document.querySelector("#reservation_btn").addEventListener('click',function(e){
                                         
                                        
                                        //let modal=document.querySelector(".modal");
                                        let choiced_date=document.querySelector("#choiced_date").value;
                                        let choiced_time=document.querySelector("#choiced_time").value;
                                        let choiced_price=document.querySelector("#choiced_price").value;
                                        let choiced_count=document.querySelector("#count_person").value;

                                        let pay_button=document.querySelector("#edit-pwd-btn");

                                        if(choiced_date===""){
                                            alert("날짜를 선택하세요")
                                           
                                            $("#pay-modal").modal({show: false});
                                             pay_button.disabled=true; 
                                         
                                            e.preventDefault();
                                       
                                            return false;
                                        }
                                        if(choiced_time===""){
                                            alert("시간을 선택하세요")
                                            document.querySelector("#reservationTime").value=""
                                            pay_button.disabled=true;
                                            
                                            e.preventDefault();
                                         
                                            return false;
                                        }
                                        
                                        if(document.querySelector("#userNo").value==="-1"){
											alert("로그인하세요");
											
                                            pay_button.disabled=true;
                                            e.preventDefault();
                                            return false;
										}
            
                                       
            
                                      document.querySelector("#reservationDate").value=choiced_date
                                      document.querySelector("#reservationDateDiv").innerText=choiced_date

                                      document.querySelector("#reservationTime").value=choiced_time
                                      document.querySelector("#reservationTimeDiv").innerText=choiced_time

                                      document.querySelector("#personalCount").value=choiced_count
                                      document.querySelector("#personalCountDiv").innerText=choiced_count+"명"

                                      document.querySelector("#payment").value=(parseInt(choiced_price)*parseInt(choiced_count))+""
                                      document.querySelector("#paymentDiv").innerText=(parseInt(choiced_price)*parseInt(choiced_count))+"원"

                                     
                                      pay_button.disabled=false;
                                     // $('#pay-modal').modal('show');
                                    })
                                })