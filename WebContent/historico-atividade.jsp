<jsp:include page="components/estrut-base.jsp" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<main class="container">
	<div class="row">
		<div class="col-md-6 mx-auto">
			<div class=" box-conteudo">
				<h2 class="text-center p-3">Hist�rico detalhado (Atividade f�sica)</h2>

				<div
					class="bg-white rounded-lg px-2 mt-4 sombreado pb-1 mb-3 box-conteudo">
					<h2 class="text-center p-3">Hist�rico recente</h2>
					<table class="table tabela-historico">
						<thead class="bg-light">
							<tr>
								<th scope="col">Atividade</th>
								<th scope="col">Dura��o (minutos)</th>
								<th scope="col">A��o</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${treinos}" var="t">
								<tr>
									<td><p>${t.atividade.nome}</p></td>
									<td><p>${t.duracao}</p></td>
									<td class="acao">
										<form action="atividade" method="post">
											<input type="hidden" name="visao" value="excluir">
											<input type="hidden" name="codigo" value="${t.codigo}" id="codigoExcluir">
											<button type="submit" class="btn btn-sm">
												<img class="apagar" src="img/trash.svg" alt="Apagar dado">
											</button>
										</form>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<a href="atividade?visao=inclusao" class="w-100 btn btn-primary mt-n3 mx-auto btn-bottom"
						role="button">Incluir novo</a>
				</div>
			</div>
			
			<div class=" box-conteudo">
				<h2 class="text-center p-3 mt-5 mb-4">Realizar inlcus�es</h2>
				<a href="peso?visao=inclusao" class="w-100 btn btn-primary mx-auto mb-3" role="button">Incluir peso</a>
	           	<a href="alimento?visao=inclusao" class="w-100 btn btn-primary mx-auto mb-4" role="button">Incluir alimento &amp; calorias</a>
	        </div>
			
		</div>
	</div>
<%-- 	<jsp:include page="components/exportar.jsp" /> --%>
</main>