//공간 정보 필요
//컨텐츠내용
//content
const urlStr = window.location.href;
$(function(){

    const url = new URL(urlStr);

    const urlParams =url.searchParams
    const spaceNo= urlParams.get('spaceNo');
    //console.log(spaceNo)
    
    getCommentList(spaceNo,callbackData)
    insertGuestComment(spaceNo);
  


// $(function(){
//     const urlStr = window.location.href;
//     const url = new URL(urlStr);

//     const urlParams =url.searchParams
//     const spaceNo= urlParams.get('spaceNo');

//     insertGuestComment();
// })

//콜백함수
function callbackData(result){
    const replyBody=document.querySelector("#space_qa_comment tbody")
    let list =[]
    for(let r of result){
        list.push({
            commentNo:r.commentNo,
            userId:r.userId,
            commentContent:r.commentContent,
            insertDate:r.insertDate,
            hostReply:r.hostReply,
            userNo:r.userNo
        })
    }
    
    document.querySelector("#QA_Count").innerHTML="("+list.length+"개)"
    drawTableList( list,replyBody)
    console.log("callback: "+list)
}



function getCommentList(spaceNo,callback){
   
     $.ajax({

        url:"select.gu",
        data:{spaceNum : spaceNo},
        success:function(result){
           // console.log(result);
            callback(result);

        },
        error:function(result){
           // console.log(result);

        }

    })



}

//댓글 목록 그리기
function drawTableList(commentList,parentTag){

    for(let reply of commentList){

        const replyRow=document.createElement('tr');
        replyRow.className="comment_list";

        const replyRow1=document.createElement('tr');
        replyRow1.className="comment_list";

        const replyRow2=document.createElement('tr');
        replyRow2.className="host_reply_title"

        const replyRow3=document.createElement('tr');
        replyRow3.className="host_reply"

        const replyRow4=document.createElement('tr');
        // replyRow4.classList.add('comment_list','hostreply')
        replyRow4.className='hostReply';

        const replyRow5=document.createElement('tr');
        replyRow5.className="comment_margin";





        replyRow.innerHTML=` <th class='nickName'>`+reply.userId+`</th>
                             <td class='mb-1'>`+reply.commentContent+`</td>
                           `

        replyRow1.innerHTML=`
                             <th class='clear'></th>
                             <td class='mb-1 time'>`+reply.insertDate+`</td> 
                            `

        replyRow2.innerHTML= `
                              <th class='clear'></th>
                            
                             ` 
 

        replyRow3.innerHTML=`<th class='clear'> </th>
                             <td> <div  class='hostReplys' class='host-reply-content mt-2'>
                                    <p class='p_class'>`+reply.hostReply+`</p>
                                  </div>
                             </td> ` 

                
                            
        replyRow5.innerHTML=`
              <td colspan='3' id='comment_line'><hr></td>
         `                           
    

         const titleTd=document.createElement("td");
         const hostTitles =document.createElement("button");
         hostTitles.className="btn btn-link  p-0 ";
         hostTitles.innerText="호스트답글";
         replyRow2.appendChild(titleTd).appendChild(hostTitles);

        replyRow3.style.display="none";    
   
                                          
      
       parentTag.appendChild(replyRow);
       parentTag.appendChild(replyRow1);
       parentTag.appendChild(replyRow2);
       parentTag.appendChild(replyRow3);
      

         // host답글
      // 방의 주인만 QandA에 대한 답글을 달거나,삭제한다.
        const hostCheck=document.querySelector("#hostCheck");
        const td=document.createElement('td');
        td.className='commentTd';
       

        console.log(hostCheck);
        if(hostCheck.value==="true"){


            // replyRow4.innerHTML=`
            //         <th class='clear'></th>
            //         <td>
            //             <div class='hostReplyDiv'  style=' width:100%; display:flex; justify-content: space-between;'>
            //                 <div >
            //                     <textarea  class='hostReplyContents' placeholder='입력하세요' style='width:100%;'  ></textarea>
            //                 </div>
            //                 <div>
            //                     <button class='enrollHostReplyBtn' type='button' >등록하기</button>
            //                 </div>
            //                 <div>
            //                     <button class='deleteHostReplyBtn' type='button'>삭제하기</button>
            //                 </div>
            //             </div>
            //         </td>
            //  `
            const th=document.createElement('th');
           // const td=document.createElement('td');
            const hostReplydiv=document.createElement('div');
            const div1=document.createElement('div');
            const div2=document.createElement('div');
            const div3=document.createElement('div');

            
            const textarea=document.createElement('textarea');
            const enrollBtn=document.createElement('button');
            const deleteBtn=document.createElement('button');

            th.className='clear';
            hostReplydiv.className='hostReplyDiv';
            hostReplydiv.style.display='flex';
            textarea.className='hostReplyContents';
            enrollBtn.className='enrollHostReplyBtn  btn btn-success';
           // enrollBtn.classList.add("enrollHostReplyBtn","btn btn-success");
     
         

            enrollBtn.innerText='등록하기'
            enrollBtn.type='button'
            deleteBtn.className='deleteHostReplyBtn'
            deleteBtn.className='enrollHostReplyBtn btn btn-danger';
            deleteBtn.innerText='삭제하기'
            deleteBtn.type='button'
       



            const relpyth=replyRow4.appendChild(th);
            const replyTd=replyRow4.appendChild(td);
            const replyContent=replyTd.appendChild(hostReplydiv);
            replyContent.appendChild(div1).appendChild(textarea)
            replyContent.appendChild(div2).appendChild(enrollBtn)
            replyContent.appendChild(div3).appendChild(deleteBtn)
           // parentTag.appendChild(replyRow4);


            // textarea.onclick=function(){
            //      replyText =  replyRow3.getElementsByClassName('p_class').innerText;
            //      console.log(replyRow3.getElementsByClassName('hostReplys  p_class'));
            //     this.value=replyText;
            // }

        
           
              insertHostComment(enrollBtn,textarea,reply.commentNo);
              deleteHostComment(deleteBtn,reply.commentNo)
          
         
        }
        else{
            replyRow4.appendChild(td);
        }

        parentTag.appendChild(replyRow4);


        
        hostTitles.onclick=function(){
            //tr태그는 table-row임 주의!!!

           if(replyRow3.style.display==="table-row"){
               replyRow3.style.display="none";
           }
           else{
               replyRow3.style.display="table-row";
           }
     }
     

    

        //자기가 등록한 QA삭제
        let userNo=parseInt(document.querySelector("#userNo").value);
        if(userNo===reply.userNo){

            const cancelTd=document.createElement('td');
            cancelTd.className='gustCancelTd  mb-1';
            const cancelButton=document.createElement('button')
            cancelButton.className='GustDeleteButton'
            cancelButton.type='button'
            cancelButton.innerHTML='삭제'

           const guestButton= replyRow.appendChild(cancelTd).appendChild(cancelButton);
            deleteGuestComment(guestButton,reply.commentNo)
        }


        parentTag.appendChild(replyRow5);
       
        
    }


}

function deleteHostComment(buttonTag,commentNo){

    buttonTag.onclick=function(){
        $.ajax({
            url:"delete.ho",
            data:{
                commentNo:commentNo,
            },
            success(result){
                alert(result);
                document.querySelector("#comment_body").innerHTML="";
                getCommentList(spaceNo,callbackData);
            },
            error(result){
                alert(result);
            }
        })
     
    }
   
}



function insertHostComment(hostButton,hostreply,commentNo){

  

   hostButton.onclick=function(){
        $.ajax({
            url:"insert.ho",
            method:"POST",
            data:{
                commentNo:commentNo,
                hostReply:hostreply.value
            },
            success(result){
                alert(result)
                hostreply.value="";
                document.querySelector("#comment_body").innerHTML="";
                getCommentList(spaceNo,callbackData);

            },
            error(result){
                alert(result)
            }
        })
       
    }

}


function insertGuestComment(spaceNo){
   const qaButton= document.querySelector("#qa_enroll");

   qaButton.onclick=function(){
  
    const qaContent=document.querySelector("#content");
    // if(qaContent.value==""){
    //     alert("내용을 입력해주세요~");
    //     return;
    // }
    
        $.ajax({
            url:"insert.gu",
            method:"POST",
            data:{
                spaceNum:spaceNo,
                content:qaContent.value
            },
            success(result){
                alert(result)
                document.querySelector("#comment_body").innerHTML="";
                getCommentList(spaceNo,callbackData);
            },
            error(result){
                alert(result)
            }
        })
        qaContent.value="";
       
    }
}

function deleteGuestComment( buttonTag,commentNo){

    buttonTag.onclick=function(){
        $.ajax({
            url:"delete.gu",
            data:{
                commentNo:commentNo,
            },
            success(result){
                alert(result);
                document.querySelector("#comment_body").innerHTML="";
                getCommentList(spaceNo,callbackData);
            },
            error(result){
                alert(result);
            }
        })
      
    }
}

})