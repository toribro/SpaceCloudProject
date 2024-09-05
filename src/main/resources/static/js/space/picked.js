const urlStrPicked = window.location.href;

window.onload = function(){

    const url = new URL(urlStrPicked);

    const urlParams =url.searchParams
    const spaceNo= urlParams.get('spaceNo')
                      
    pickedview(spaceNo);
    picked(spaceNo);




function pickedview(spaceNo){
   
 
    let pickedText=document.querySelector("#picked_divs");
    
    
    $.ajax({

        url:'pickedcheck.sp',
        type:'GET',
    
        data:{
            spaceNum:spaceNo

        },

        success:function(response){
       
        
        if(response==="찜하기"){
            pickedText.innerHTML=response+"<i class='fa-regular fa-heart'></i>"
        }
        else if(response==="찜해제"){
            pickedText.innerHTML=response+"<i class='fa-solid fa-heart' style='color:red'></i>"
        }
        
       },
        error:function(error){
            console.log("error"+error);

        }


    })

    
    
}
       
       
function picked(spaceNo){
      

     let pickedText=document.querySelector("#picked_divs");
      document.querySelector("#picked_divs").addEventListener('click',function(){


        $.ajax({

            url:'picked.sp',
            type:'GET',
        
            data:{
                spaceNum:spaceNo

            },

            success:function(response){
            console.log(response);
            
            if(response==="찜하기"){
                pickedText.innerHTML=response+"<i class='fa-regular fa-heart'></i>"
                alert("찜해제되었습니다.")
            
            }
            else if(response==="찜해제"){
                pickedText.innerHTML=response+"<i class='fa-solid fa-heart' style='color:red'></i>"
                alert("찜되었습니다.")
            }
            else if(response==="로그인하세요"){
                alert(response)
            }
            
        },
            error:function(error){
                console.log("error"+error);

            }


        })




      });

                
           

 }
    
    
      

      // if(_this.innerText==="찜하기"){
      //     _this.innerText="찜해제";
      //     alert("찜해제되었습니다.")
      // }
      // else{
      //     _this.innerText="찜하기";
      //     alert("찜하기");
      // }



   
};
