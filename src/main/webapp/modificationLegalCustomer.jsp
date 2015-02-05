 <!DOCTYPE html> 
 <html> 
 <head lang="en">
     <script>
         function init(){
         document.getElementsByName("economicId")[0].value = "<%=request.getParameter("economicId")%>";}
     </script>
   <meta charset="UTF-8"> 
   <title>Modification</title>
   <style> 
       body {background-color:midnightblue} 
       h1   {color:yellow} 
       button    {color:black  background-color: lightyellow} 
       h3 {color: yellow} 
       div.one { border:2px solid border-color: yellow margin:20px padding:20px width: 93%} 
       div.two { border:2px solid border-color: yellow margin:20px padding:20px width: 93%}
       p {color: yellow  align-content: center}
       textarea {background-color: lightyellow  } 
   </style> 
 </head>

 <body onload="init()">

 <h1>Modification</h1>
 <form method="post" action="ModificationLegalCustomer">
 <input type="hidden" name="economicId"/>
 <div class="one">
   <h3>Modification a Legal customer </h3>
   <p> 
        <div class="two">
            <p class="one">
                    Name:  <input cols="50" rows="1" name="name"/>
                </p> 
           <p class="one">
                   Registration Date:  <input cols="50"rows="1"name="registerDate"/>
               </p> 
           <p class="one"> 
                   Customer Code:  <input cols="50"rows="1"name="customerId"/>
               </p> 
           <p class="one"> 
                   <button cols="50" rows="1" onclick="change()">Sign Up!</button>
               </p> 
        </div> 
   </p> 
 </div>

 </form>
 <script>
     function change(){

     }
 </script>
 </body> 
 </html> 