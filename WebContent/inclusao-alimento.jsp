<jsp:include page="components/estrut-base.jsp" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<main class="container">
	<div class="row">
		<div class="col-lg-6 mx-auto">
			<div class=" box-conteudo">
			<c:if test="${not empty erro }">
				<div class="mt-4 alert alert-danger" >${erro}</div>
			</c:if>
			<c:if test="${not empty exito}">
				<jsp:include page="components/modal-sucesso.jsp" />
			</c:if>
				<h2 class="text-center p-3">Inclusão de nova refeição</h2>
				<form class="form-login-page ht-form mt-0" action="alimento"
					method="post">
					<input type="hidden" value="cadastrar" name="visao">
					<div class="form-group form-insert">
						<label for="alimento"></label> 
						<input type="text" id="alimento" name="nome-almnt" class="form-control p-2 px-5 text-left sombreado inp-incluir" placeholder="Nome alimento"> 
						<label for="calorias"></label>
						<input type="number" id="calorias" step="0.1" name="qtd-cal" class="form-control p-2 px-5 text-left sombreado" placeholder="Calorias">
						<label for="data-insert"></label>
						<input type="text" id="data-insert" name="data-ref" class="form-control p-2 px-5 text-left mb-3 sombreado inp-incluir" placeholder="dd/mm/aaaa">
					</div>
					<button type="submit" class="w-100 btn btn-primary mx-auto mb-5">Incluir</button>
				</form>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-lg-6 mx-auto">
			<div
				class="bg-white rounded-lg px-2 sombreado pb-1 mb-3 box-conteudo">
				<h2 class="text-center p-3">Histórico recente</h2>

				<table class="table table-borderless tabela-historico">
					<thead class="bg-light">
						<tr>
							<th scope="col">Alimento</th>
							<th scope="col">Calorias <i>(total)</i></th>
							<th scope="col">Data</th>
							<th scope="col">Ação</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${refeicoes}" var="r">
							<tr>
								<td><p>${r.alimento.nome}</p></td>
								<td><p>${r.alimento.caloria}</p></td>
								<td><p><fmt:formatDate value="${r.data.time}" pattern="dd/MM/yyyy"/></p></td>
								<td class="acao">
									<form action="alimento" method="post">
										<input type="hidden" name="visao" value="excluir"> <input
											type="hidden" name="codigo" value="${r.codigo}"
											id="codigoExcluir">
										<button type="submit" class="btn btn-sm">
											<img class="apagar" src="img/trash.svg" alt="Apagar dado">
										</button>
									</form>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<a href="alimento?visao=historico"
					class="w-100 btn btn-primary mt-n3 mx-auto btn-bottom" role="button">Carregar todos</a>
			</div>
			<div class=" box-conteudo">
				<h2 class="text-center p-3 mt-5 mb-4">Outras inclusões</h2>
				<a href="atividade?visao=inclusao" 
					class="w-100 btn btn-primary mx-auto mb-3" role="button">Incluir
					atividade física</a> 
				<a href="peso?visao=inclusao"
					class="w-100 btn btn-primary mx-auto mb-4" role="button">Incluir
					peso</a>
			</div>
		</div>
	</div>
</main>