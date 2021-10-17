<jsp:include page="components/estrut-base-min.jsp" />
<link rel="stylesheet" href="css/login-page.css">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="container">
	<div class="row">
		<div class="col-md-6 mx-auto">
			<a href="#" class="d-flex justify-content-center"> 
				<img src="img/logo.svg" alt="HealthTrack">
			</a>
			
			<c:if test="${not empty erroLogin}">
				<div class="mt-4 alert alert-danger" >${erroLogin}</div>
			</c:if>
			
			
			<div class="mt-4 alert alert-warning pt-4" >
				<p><strong>email:</strong> rm85960@fiap.com.br</p>
				<p><strong>senha:</strong> default</p> 
			</div>
			
			<form class="form-login-page" action="login" method="post">
				<div class="form-group ht-form">
					<label for="email">E-mail</label>
						<input 	type="text"
								id="email"
								name="email"
								class="form-control p-4 mb-4 sombreado"
								placeholder="ex.: john@doe.com">
				</div>
				<div class="form-group ht-form">
					<label for="senha" class="d-flex justify-content-between">Senha
						<a href="#">Esqueceu a senha?</a>
					</label>
						<input type="password" id="senha" name="senha" class="form-control p-4 mb-4 sombreado" placeholder="digite sua senha">
				</div>
				<button type="submit" class="btn btn-primary mx-auto mb-4 w-100">Logar <i>(sem senha)</i></button>
			</form>

			<a href="#" class="cadastro-call">Não tem cadastro? Clique aqui</a>
			<a href="#" class="btn btn-clean w-100 text-center">
				<img src="img/google.svg" alt=" ">
				<span>Entre com o Google</span>
			</a>
			<a href="#" class="btn btn-clean w-100 text-center">
				<img src="img/facebook.svg" alt=" ">
				<span>Entre com o Facebook</span>
			</a>
		</div>
	</div>
</div>
