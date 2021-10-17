$(function(){
   $('.menu-topo').click(function() {
      $(document.documentElement).toggleClass('menu-aberto');
   })
   
   const data = new Date();
   $("input#data-insert").val(data.getDate() + "/" + (data.getMonth() + 1) +  "/" + data.getFullYear());
   
});