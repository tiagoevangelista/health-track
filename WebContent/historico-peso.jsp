<jsp:include page="components/estrut-base.jsp" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<main class="container">
	<div class="row">
		<div class="col-md-6 mx-auto">

			<div class=" box-conteudo">
				<h2 class="text-center p-3">Histórico detalhado (Peso)</h2>

				<div
					class="bg-white rounded-lg px-2 mt-4 sombreado pb-1 mb-3 box-conteudo">
					<h2 class="text-center p-3">Histórico recente</h2>

					<table class="table tabela-historico">
						<thead class="bg-light">
							<tr>
								<th scope="col">Peso (Kg)</th>
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
					<a href="#" class="w-100 btn btn-primary mt-n3 mx-auto btn-bottom"
						role="button">Carregar mais antigos</a>
				</div>
			</div>
			
			<div class=" box-conteudo">
	           <h2 class="text-center p-3 mt-5 mb-4">Realizar inclusão</h2>
	           <a href="atividade?visao=inclusao" class="w-100 btn btn-primary mx-auto mb-3" role="button">Incluir atividade</a>
	           <a href="alimento?visao=inclusao" class="w-100 btn btn-primary mx-auto mb-4" role="button">Incluir alimento &amp; calorias</a>
	        </div>
			
		</div>
	</div>
<%-- 	<jsp:include page="components/exportar.jsp" /> --%>
</main>