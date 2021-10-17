<jsp:include page="components/estrut-base.jsp" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
	
<main class="container">
  <div class="row">
     <div class="col-md-6 mx-auto">
        <div class=" box-conteudo">
        	<c:if test="${not empty erro}">
				<div class="mt-4 alert alert-danger" >${erro}</div>
			</c:if>
			<c:if test="${not empty exito}">
				<jsp:include page="components/modal-sucesso.jsp" />
			</c:if>
			
           <h2 class="text-center p-3">Inclusão de novo peso</h2>
           
           <form class="form-login-page ht-form mt-0" action="peso" method="post">
			  <input type="hidden" value="cadastrar" name="visao">
              <div class="form-group form-insert">
                 <label for="peso"></label>
                 <input type="number" id="peso" step="0.1" name="nr-peso" class="form-control p-2 px-5 text-left sombreado" value="60">
                 
                 <label for="data-insert"></label>
                 <input type="text" id="data-insert" name="data-peso" class="form-control p-2 px-5 text-left mb-3 sombreado inp-incluir">
              </div>
			  <button type="submit" class="w-100 btn btn-primary mx-auto mb-5">Incluir</button>
           </form>
        </div>
     </div>
  </div>
  <div class="row">
     <div class="col-md-6 mx-auto">
        <div class="bg-white rounded-lg px-2 sombreado pb-1 mb-3 box-conteudo">
           <h2 class="text-center p-3">Histórico recente</h2>
           <table class="table table-borderless tabela-historico">
              <thead class="bg-light">
                 <tr>
                    <th scope="col">Peso</th>
                    <th scope="col">Data</th>
					<th scope="col">Ação</th>
                 </tr>
              </thead>
              <tbody>
                 <c:forEach items="${pesos}" var="p">
					<tr>
						<td class="anterior"><p><strong>${p.peso}</strong></p></td>
						<td class="ultima"><p><fmt:formatDate value="${p.data.time}" pattern="dd/MM/yyyy"/></p></td>
						<td class="acao">
							<form action="peso" method="post">
								<input type="hidden" name="visao" value="excluir">
								<input type="hidden" name="codigo" value="${p.codigo}" id="codigoExcluir">
								<button type="submit" class="btn btn-sm">
									<img class="apagar" src="img/trash.svg" alt="Apagar dado">
								</button>
							</form>
						</td>							
					</tr>
				</c:forEach>
              </tbody>
           </table>
           <a href="./historico.html" class="w-100 btn btn-primary mt-n3 mx-auto btn-bottom" role="button">Carregar mais antigos</a>
        </div>
        <div class=" box-conteudo">
           <h2 class="text-center p-3 mt-5 mb-4">Outras inclusões</h2>
 			<a href="inclusao-atividade.jsp" class="w-100 btn btn-primary mx-auto mb-3" role="button">Incluir atividade física</a>
           	<a href="inclusao-alimento.jsp" class="w-100 btn btn-primary mx-auto mb-4" role="button">Incluir alimento &amp; calorias</a>
        </div>
     </div>
  </div>
</main>