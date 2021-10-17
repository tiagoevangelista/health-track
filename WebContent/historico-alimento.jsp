<jsp:include page="components/estrut-base.jsp" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<main class="container">
	<div class="row">
		<div class="col-md-6 mx-auto">
			<div class=" box-conteudo">
				<h2 class="text-center p-3">Histórico detalhado (Refeições)</h2>

				<div
					class="bg-white rounded-lg px-2 mt-4 sombreado pb-1 mb-3 box-conteudo">
					<h2 class="text-center p-3">Histórico recente</h2>
					<table class="table tabela-historico">
						<thead class="bg-light">
							<tr>
								<th scope="col">Alimento</th>
								<th scope="col">Calorias totais (Kcal)</th>
								<th scope="col">Ação</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${refeicoes}" var="r">
								<tr>
									<td><p>${r.alimento.nome}</p></td>
									<td><p>${r.alimento.caloria}</p></td>
									<td class="acao">
										<form action="alimento" method="post">
											<input type="hidden" name="visao" value="excluir">
											<input type="hidden" name="codigo" value="${r.codigo}" id="codigoExcluir">
											<button type="submit" class="btn btn-sm">
												<img class="apagar" src="img/trash.svg" alt="Apagar dado">
											</button>
										</form>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<a href="alimento?visao=inclusao" class="w-100 btn btn-primary mt-n3 mx-auto btn-bottom"
						role="button">Adicionar novo</a>
				</div>
			</div>
			
			<div class=" box-conteudo">
	           <h2 class="text-center p-3 mt-5 mb-4">Realizar inlcusões</h2>
	           <a href="peso?visao=inclusao" class="w-100 btn btn-primary mx-auto mb-3" role="button">Incluir peso</a>
	           <a href="atividade?visao=inclusao" class="w-100 btn btn-primary mx-auto mb-4" role="button">Incluir atividade</a>
	        </div>
			
		</div>
	</div>
<%-- 	<jsp:include page="components/exportar.jsp" /> --%>
</main>