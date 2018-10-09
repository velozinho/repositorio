$(function() {

	$('#btn-cadastro').on('click', function() {
		$('#container').load('cadastroPessoas.html');

	})

	$('#cep').focusout(function() {
		var cep = $('#cep').val();
		$.get('https://viacep.com.br/ws/'+cep+'/json/', function(json) {
			$('#logradouro').val(json.logradouro);
			$('#bairro').val(json.bairro);
			$('#cidade').val(json.localidade);
			$('#uf').val(json.uf);
		})
	})

	$('#container').on('click','#btn-salvar',function() {
		var op = $('#btn-salvar').val();
				jsonPessoa = {};
				jsonPessoa['nome'] = $('#nome').val();
				jsonPessoa['cpf'] = $('#cpf').val();
				jsonPessoa['email'] = $('#email').val();

				jsonEndereco = {};
				jsonEndereco['cep'] = $('#cep').val();
				jsonEndereco['logradouro'] = $('#logradouro').val();
				jsonEndereco['cidade'] = $('#cidade').val();
				jsonEndereco['numero'] = $('#numero').val();
				jsonEndereco['complemento'] = $('#complemento').val();
				jsonEndereco['bairro'] = $('#bairro').val();
				jsonEndereco['uf'] = $('#uf').val();

				jsonPessoa['endereco'] = jsonEndereco;
				if(op == 'editar'){
					$.ajax({
						url:'/exercicio-manterpessoa/Pessoas',
						method:'PUT',
						data:JSON.stringify(jsonPessoa)
					}).done(function(){
						$('#container').html('');
					})
				}else{
				$.post('/exercicio-manterpessoa/Pessoas', JSON
						.stringify(jsonPessoa), function() {
					console.log('Pessoa salva com sucesso');
				})
				}
			})

	$('#btn-mostraPessoas').on('click',function() {
				$.get('/exercicio-manterpessoa/Pessoas', function(jsons) {
					var table = $('<table>').attr('id', 'tabela');
					for (i in jsons) {
						var btnExcluir = $('<button>').html('Excluir')
								.addClass('btn-excluir');
						var btnEdit = $('<button>').html('Editar').addClass('btn-editar');
						var td1 = $('<td>').html(jsons[i]['nome']);
						var td2 = $('<td>').html(jsons[i]['cpf']).addClass('cpfPessoa');
						var td3 = $('<td>').html(jsons[i]['email']);
						var td4 = $('<td>').html(jsons[i]['endereco']['cep']);
						var td5 = $('<td>').append(btnEdit);
						var td6 = $('<td>').append(btnExcluir);

						tr = $('<tr>').append(td1).append(td2).append(td3)
								.append(td4).append(td5).append(td6);
						$(table).append(tr);
					}

					$('#container').html(table);
				})

			})

	$('#container').on('click', '.btn-editar', function() {
		var tr = $(this).closest('tr');
		var linha = [];
		var i = 0;
		$(tr).find('td').each(function() {
			linha[i] = $(this).html();
			i++;
		})
		$('#container').load('cadastroPessoas.html',function(){
			$('#btn-salvar').val('editar');
			$('#nome').val(linha[0]);
			$('#cpf').prop('disabled',true);
			$('#cpf').val(linha[1]);
			$('#email').val(linha[2]);
			$('#cep').val(linha[3]);
		});
		console.log('carreguei antes')
	})

	$('#container').on('click', '.btn-excluir', function() {
		var cpf = $(this).closest('tr').find('.cpfPessoa').html();
		$.ajax({
			url : '/exercicio-manterpessoa/Pessoas?cpf=' + cpf,
			method : 'DELETE',
			success : function(result) {
				// Do something with the result
			}
		});

	})

})


//'https://viacep.com.br/ws/13568818/json/'
//alterar json.localidade para cidade se usar a API do Erick

