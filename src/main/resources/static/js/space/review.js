
//지역변수로 설정 ..
//공간 정보 필요
//컨텐츠내용
//content

const urlStrReview = window.location.href;

$(function(){

    const url = new URL(urlStrReview);

    const urlParams =url.searchParams
    const spaceNo= urlParams.get('spaceNo');
    console.log(spaceNo);

    getReviewList(spaceNo,callbackReview)
    insertReview(spaceNo);
  


// $(function(){
//     insertReview();
// })

//콜백함수
function callbackReview(result){
    const replyBody=document.querySelector("#space_review tbody")
    let list =[]
    for(let r of result){
        list.push({
            reviewNo:r.reviewNo,
            reviewContent:r.content,
            reviewInsertDate:r.insertDate,
            reviewUserName:r.userName,
            reviewUserNo:r.userNo,
            reviewStar:r.reviewStar
        })
    }
    document.querySelector("#review_count").innerHTML="("+list.length+"개)"
    drawTableList( list,replyBody)
    console.log("callback: "+list)
}



function getReviewList(spaceNo,callback){
   
     $.ajax({

        url:"review.sp",
        data:{spaceNum : spaceNo},
        success:function(result){
            console.log(result);
            callback(result);

        },
        error:function(result){
            console.log(result);

        }

    })



}

//댓글 목록 그리기
function drawTableList(reviewList,parentTag){

    for(let reply of reviewList){
        const replyRow=document.createElement('tr');
        replyRow.className="review_list";
        const replyRow1=document.createElement('tr');
        const replyRow2=document.createElement('tr');

        const replyStar=document.createElement('tr');

        // <input type="radio" class="star" value="1">

         let str='';
         for(let i=0; i<reply.reviewStar; i++){
           str+='★ ';
         }


        replyRow.innerHTML=` <th class='nickName'>`+reply.reviewUserName+`</th>
                             <td class='mb-1'>`+reply.reviewContent+`</td>
                          
                             `
          
        replyStar.innerHTML=` <th class='clear'></th>
                              <td class='mb-1 starColor'>`+ str+`</td>
                            `

        replyRow1.innerHTML=`
                             <th class='clear'></th>
                             <td class='mb-1 time'>`+reply.reviewInsertDate+`</td> 
                             ` 
        replyRow2.innerHTML=`
                             <td colspan='3' id='comment_line'><hr></td>
                            `                           

                   



        parentTag.appendChild(replyRow);
        parentTag.appendChild(replyStar);
        parentTag.appendChild(replyRow1);
        parentTag.appendChild(replyRow2);

        replyRow.className='comment_list';
        replyRow1.className='comment_list';
        replyRow2.className='comment_margin';
      //  submitHostReplyBtn
      
       //리뷰를 쓴 등록되어있는 유저

       let userNo=parseInt(document.querySelector("#userNo").value);
       //let userId=parseInt(document.querySelector("#userId").value);
       const cancelTd=document.createElement('td');
       cancelTd.className="gustCancelTd  mb-1";

        if((reply.reviewUserNo===userNo)){
           // const cancelTd=document.createElement('td');
            const buttonTag=document.createElement('button');
           // buttonTag.className='submitHostReplyBtn';
            buttonTag.type='button'

            const cancelTdButton=cancelTd.appendChild(buttonTag);
            cancelTdButton.innerText="삭제";
            cancelTdButton.className='GuestReviewButon'
            replyRow.appendChild(cancelTd);

            deleteButton(buttonTag,reply.reviewNo)
        }
        else{
        
            replyRow.appendChild(cancelTd);
        }
       
    }


}

function deleteButton(buttonTag,reviewNo){

    buttonTag.onclick=function(){
        $.ajax({
            url:"delete.re",
            data:{
                reviewNo:reviewNo,
            },
            success(result){
                alert(result);
                document.querySelector("#review_body").innerHTML="";
                getReviewList(spaceNo,callbackReview);
            },
            error(result){
                alert(result);
            }
        })
      
    }
   
}



function insertReview(spaceNo){

   const reviewButton= document.querySelector("#reivew_enroll");
   
   

   reviewButton.onclick=function(){
    const reviewContent=document.querySelector("#reivew_content");
    const reviewStar=document.querySelector('input[name="rating"]:checked');
    if(reviewStar==null){
        alert("별점을 입력하세요");
        return
    }

        $.ajax({
            url:"insert.re",
            method:"POST",
            data:{
                spaceNum:spaceNo,
                reviewStar:reviewStar.value,
                content:reviewContent.value
            },
            success(result){
                alert(result)
                reviewContent.value="";
                document.querySelector("#review_body").innerHTML="";
                getReviewList(spaceNo,callbackReview);

            },
            error(result){
                alert(result)
            }
        })
      
    }

}

})