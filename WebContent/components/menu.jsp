<header class="bg-white sombreado topo">
   <div class="container">
      <div class="d-flex flex-row justify-content-between py-3">
         <a href="index.jsp">
            <img src="img/logo.svg" alt="HealthTrack">
         </a>
         <div class="menu-topo">
            <img src="img/menu-hamburger.svg" alt="Menu de navegação" class="abrir" role="button">
            <img src="img/menu-fechar.svg" alt="Menu de navegação" class="fechar" role="button">
         </div>
      </div>
      <nav class="bg-white menu-nav">
         <ul class="px-0">
<!--             <li class="p-3 d-flex align-items-center justify-content-between"> -->
<!--                <a href="atualizacao.jsp" class="d-flex align-items-center justify-content-between w-100"> -->
<!--                   <span>Aletrar perfil</span> -->
<!--                   <img src="img/config.svg" alt="Alterar perfil"> -->
<!--                </a> -->
<!--             </li> -->
            <li class="mb-4">
               <h2 class="bg-light py-2 px-3 mb-2">Fazer inclusão</h2>
               <ul class="px-3">
                  <li><a href="peso?visao=inclusao">Peso</a></li>
                  <li><a href="atividade?visao=inclusao">Atividade física</a></li>
                  <li><a href="alimento?visao=inclusao">Alimento ingerido</a></li>
               </ul>
            </li>
            <li class="mb-4">
               <h2 class="bg-light py-2 px-3 mb-2">Histórico detalhado</h2>
               <ul class="px-3">
                  <li><a href="peso?visao=historico">Peso</a></li>
                  <li><a href="atividade?visao=historico">Atividades físicas</a></li>
                  <li><a href="alimento?visao=historico">Alimento &amp; Calorias</a></li>
               </ul>
            </li>
            <li class="pt-3 border-top pr-3 text-right btn-sair">
               <a href="login">
                  <span class="mr-2 text-danger">Encerrar sessão</span>
                  <button class="btn btn-danger full-rounded-ico"><img src="img/power.svg" alt="Encerrar sessão"></button>
               </a>
            </li>
         </ul>
      </nav>
   </div>
</header>
