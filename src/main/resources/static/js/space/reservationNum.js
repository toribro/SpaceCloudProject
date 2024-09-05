

document.addEventListener('DOMContentLoaded', function () {
    let num= document.querySelector("#count_person");
    let max= parseInt(document.querySelector("#MaxPerson").innerText)
    let min=1;
    num.value=1;

    document.querySelector("#plus").onclick=function(){
   
    
     
     
       if( num.value>=max){
           alert("최대"+max+"명까지입니다.");
          return false;
       }

      
       num.value++;
     
   }
   document.querySelector("#minus").onclick=function(){
		
    
   
       if( num.value<= min){
          
           alert("최소인원은"+min+"명입니다.");
           return false;
       }
     
       num.value--;
    
   }
 })