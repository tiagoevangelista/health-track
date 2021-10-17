<jsp:include page="components/estrut-base.jsp" />

	
<main class="container">
   <div class="row">
      <div class="col-md-6 mx-auto">
         <div class=" box-conteudo">
            <h2 class="text-center p-3">Alterar dados iniciais</h2>
            
            <form class="form-login-page ht-form mt-3" action="#" method="get">
               <div class="form-group">
                  <label for="nome">Nome</label>
                  <input type="text" id="nome" class="form-control p-4 mb-3 sombreado" value="John Doe">
               </div>
               
               <div class="form-row">
                  <div class="form-group col-7">
                     <label for="meta-peso">Meta a ser atingida</label>
                     <input type="number" id="meta-peso" class="form-control p-4 mb-2 sombreado" value="65">
                  </div>
                  <div class="form-group col-5">
                     <label for="altura">Altura</label>
                     <input type="text" id="altura" class="form-control p-4 mb-2 sombreado" value="altura">
                  </div>
               </div>
               <button type="submit" class="w-100 btn btn-primary mx-auto mb-3">Salvar alterações de dados</button>
            </form>
            <h2 class="text-center p-3 mt-5">Dados de conta</h2>

            <form class="form-login-page ht-form mt-3" action="#" method="get">
               <div class="form-group">
                  <label for="email">Nome</label>
                  <input type="text" id="email" class="form-control p-4 mb-3 sombreado" value="john@doe.com">
               </div>
               <div class="form-group">
                  <label for="senha">Alterar senha</label>
                  <input type="password" id="senha" class="form-control p-4 mb-3 sombreado" placeholder="Digite a nova senha">
                  <input type="password" class="form-control p-4 mb-4 sombreado" placeholder="Confirme a nova senha">
               </div>
               <button type="submit" class="w-100 btn btn-primary mx-auto mb-5">Salvar alterações de conta</button>
            </form>
         </div>
      </div>
   </div>
</main>
