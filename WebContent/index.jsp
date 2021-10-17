<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<c:if test="${not empty user}">
<jsp:include page="components/estrut-base.jsp" />
<main class="container">
   <div class="row">
      <div class="text-center dsclp">Desculpe, professor, as funcionalidades da home não foram implementadas ainda, use o <i>"menu hamburguer"</i>, por gentileza.<br><br>Lá as funções de Cadastrar, Resumo dos três últimos cadastros, o Histórico completo e a Exclusão já foram implementados :)</div>
      <div class="col-md-6">
         <div class="bg-white rounded-lg px-2 sombreado pb-1 mb-3 box-conteudo">
            <h2 class="text-center p-3">Resumo do progresso</h2>
            
            
            <table class="table table-borderless tabela-historico">
               <thead>
                  <tr>
                     <th scope="col">Mês anterior</th>
                     <th scope="col">Última atualização</th>
                  </tr>
               </thead>
               <tbody>
                  <tr>
                     <td class="anterior">
                        <span class="font-weight-bold text-uppercase">Peso</span>
                        <p><strong>68.6</strong>KG</p>
                     </td>
                     <td class="ultima">
                        <span class="font-weight-bold text-uppercase">Peso</span>
                        <p><strong>70</strong>KG</p>
                     </td>
                  </tr>
                  <tr>
                     <td class="anterior">
                        <span class="font-weight-bold text-uppercase">IMC</span>
                        <p><strong>21.9</strong></p>
                     </td>
                     <td class="ultima">
                        <span class="font-weight-bold text-uppercase">IMC</span>
                        <p><strong>22.1</strong></p>
                     </td>
                  </tr>
                  <tr>
                     <td class="anterior">
                        <span class="font-weight-bold text-uppercase">Dia de exercícios</span>
                        <p><strong>8</strong> dias</p>
                     </td>
                     <td class="ultima">
                        <span class="font-weight-bold text-uppercase">Dia de exercícios</span>
                        <p><strong>3</strong> dias</p>
                     </td>
                  </tr>
               </tbody>
            </table>

         </div>
      </div>
      
      <div class="col-md-6 mb-5">
         <div class="bg-white rounded-lg px-2 pb-4 sombreado box-conteudo">
            <h2 class="text-center p-3">Inclusão Rápida</h2>
            
            <form class="px-3">
               <div class="form-group form-insert mb-4 border-bottom pb-3">
                  <label for="peso" class="text-uppercase font-weight-bold mt-3 mb-1">Peso</label>
                  <div class="input-group mb-3">
                     <input type="number" class="form-control bg-light border" placeholder="0,0" aria-label="Inserir peso">
                     <div class="input-group-append">
                        <button class="btn btn-primary btn-outline-secondary" type="button" data-toggle="modal" data-target="#dadoIncluido">Incluir</button>
                     </div>
                  </div>
               </div>
               <div class="form-group form-insert mb-4">
                  <label for="alimento" class="text-uppercase font-weight-bold mb-1">Alimento</label>
                  <div class="input-group mb-3">
                     <input type="text" class="form-control bg-light border py-3" placeholder="Alimento" aria-label="Inserir peso">
                     <div class="input-group-append">
                        <button class="btn btn-primary btn-outline-secondary" type="button" data-toggle="modal" data-target="#dadoIncluido">Incluir</button>
                     </div>
                  </div>
               </div>
               <div class="form-group form-insert border-bottom pb-3 ">
                  <label for="peso" class="text-uppercase font-weight-bold mb-1">Calorias</label>
                  <div class="input-group mb-3">
                     <input type="number" class="form-control bg-light border" placeholder="0,0" aria-label="Recipient's username" aria-describedby="button-addon2">
                     <div class="input-group-append">
                        <button class="btn btn-primary btn-outline-secondary" type="button" id="button-addon2" data-toggle="modal" data-target="#dadoIncluido">Incluir</button>
                     </div>
                  </div>
               </div>
               <div class="form-group form-insert mb-4">
                  <label for="alimento" class="text-uppercase font-weight-bold mb-1">Alimento</label>
                  <div class="input-group mb-3">
                     <input type="text" class="form-control bg-light border py-3" placeholder="Nome do exercício" aria-label="Inserir peso">
                  </div>
                  <div class="input-group mb-3">
                     <input type="number" class="form-control bg-light border py-3 texto-pqn" placeholder="Duração (min)" aria-label="Inserir peso">
                     <div class="input-group-append">
                        <button class="btn btn-primary btn-outline-secondary" type="button" data-toggle="modal" data-target="#dadoIncluido">Incluir</button>
                     </div>
                  </div>
               </div>
            </form>
         </div>
      </div>
   </div>
</main>
</c:if>

<c:if test="${empty user}">
	<jsp:include page="components/estrut-base-min.jsp" />
	<div class="container">
		<div class="mt-4 alert alert-danger text-center" >
			<div class="mb-3">
				<img src="img/logo.svg" alt="HealthTrack">
			</div>
	 		<p>Você precisa logar no sistema</p>
		 	<a href="login">clique aqui para ser redirecionado</a>
		 </div>
	</div>
</c:if>