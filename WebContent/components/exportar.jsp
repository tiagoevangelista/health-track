<div class="row">
   <div class="col-md-6 mx-auto">
      <div class=" box-conteudo mt-5">
         <p><img src="img/filter.svg" alt="Filtos avançados"> Filtros avançados</p>

         <form class="form-login-page ht-form mt-3" action="#">
            <div class="form-row">
               <div class="form-group col-6">
                  <label for="data-de">De:</label>
                  <input type="date" id="data-de" class="form-control p-3 pr-0 h-auto mb-2 sombreado">
               </div>
               <div class="form-group col-6">
                  <label for="data-ate">Até:</label>
                  <input type="date" id="data-ate" class="form-control p-3 h-auto mb-2 sombreado">
               </div>
            </div>
            <div class="form-group">
               <label for="tipo-progresso">Tipo de progresso</label>
               <select class="custom-select p-3" id="tipo-progresso">
                  <option selected disabled>Tipo de progresso...</option>
                  <option value="1">Ganho de peso</option>
                  <option value="2">Perda de peso</option>
                </select>
            </div>
            <div class="form-group mt-5">
               <label for="formato-export">Exportar em:</label>
               <select class="custom-select p-3" id="formato-export">
                  <option selected disabled>Formato para exportação...</option>
                  <option value="1">.xlsx</option>
                  <option value="2">.cvs</option>
                </select>
            </div>
            <button type="submit" class="w-100 mt-4 btn btn-primary mx-auto mb-5">Exportar dados</button>
         </form>
      </div>
   </div>
</div>