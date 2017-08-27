<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %> 
<html> 
 <head> 
 <title>this is  a test</title> 
 <script type="text/javascript"> 
 function changeNext(){ 
  var a=document.getElementById("img"); 
  a.src="ValidateCodeServlet?a="+new Date().getTime(); 
 } 
</script>
 </head> 
 <body> 
 <form action="" method="get"> 
  <table align="center"> 
   <tr> 
    <td><img id="img" alt="" src="ValidateCodeServlet" onclick="changeNext()"></td> 
   </tr> 
  </table> 
 </form> 
 </body> 
</html> 
