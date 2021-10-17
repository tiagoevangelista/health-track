
<script>
	document.addEventListener("DOMContentLoaded", function() {
		$("#dadoIncluido").modal("show");
	});
</script>

<div class="modal fade ht-modal text-center" id="dadoIncluido" tabindex="1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
   <div class="modal-dialog modal-dialog-centered">
      <div class="modal-content">
         <div class="modal-body">
            <h2 class="mb-5">Dado incluído</h2>
            <p>Você também pode: </p>
            <a href="./index.jsp" class="w-100 btn btn-primary mt-3 mx-auto" role="button">Ir para Home</a>
            
            <a href="atividade?visao=historico" class="w-100 btn btn-primary mt-5 mx-auto" role="button">Histórico atividade fisica</a>
            <a href="peso?visao=historico" class="w-100 btn btn-primary mt-3 mx-auto" role="button">Histórico peso</a>
            <a href="alimento?visao=historico" class="w-100 btn btn-primary mt-3 mx-auto" role="button">Histórico alimento &amp; caloria</a>
            
            <a href="atividade?visao=inclusao" class="w-100 btn btn-primary mt-5 mx-auto" role="button">Incluir atividade física</a>
            <a href="peso?visao=inclusao" class="w-100 btn btn-primary mt-3 mx-auto" role="button">Incluir Peso</a>
            <a href="alimento?visao=inclusao" class="w-100 btn btn-primary mt-3 mx-auto" role="button">Incluir alimento &amp; caloria</a>
         </div>
         <div class="modal-footer">
            <button type="button" class="btn btn-secondary mx-auto" data-dismiss="modal">Fechar</button>
          </div>
      </div>
   </div>
</div>