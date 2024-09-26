import requests
import uuid

# Gerar um nome de usuário único
username = f"usuario_{uuid.uuid4()}"
password = "Senha@123"

# Criar um usuário
response = requests.post(
    "https://demoqa.com/Account/v1/User",
    json={"userName": username, "password": password}
)

if response.status_code == 201:
    print("Usuário criado com sucesso!")

    # Gerar um token de acesso
    token_response = requests.post(
        "https://demoqa.com/Account/v1/GenerateToken",
        json={"userName": username, "password": password}
    )

    if token_response.status_code == 200:
        token = token_response.json().get("token")
        print("Token gerado com sucesso!")

        # Confirmar se o usuário está autorizado
        auth_response = requests.get(
            "https://demoqa.com/Account/v1/Authorized",
            headers={"Authorization": f"Bearer {token}"}
        )

        if auth_response.status_code == 200:
            print("Usuário autorizado.")

            # Listar os livros disponíveis
            books_response = requests.get("https://demoqa.com/BookStore/v1/Books")
            if books_response.status_code == 200:
                books = books_response.json().get("books", [])
                print("Livros disponíveis:")
                for book in books:
                    print(book["title"])

                # Reservar dois livros (exemplo com os primeiros dois)
                if len(books) >= 2:
                    book_ids = [books[0]["isbn"], books[1]["isbn"]]
                    reserve_response = requests.post(
                        "https://demoqa.com/BookStore/v1/Books",
                        headers={"Authorization": f"Bearer {token}"},
                        json={"userId": response.json()["userID"], "collectionOfIsbns": [{"isbn": book_id} for book_id in book_ids]}
                    )

                    if reserve_response.status_code == 201:
                        print("Livros reservados com sucesso!")
                    else:
                        print(f"Erro ao reservar livros: {reserve_response.text}")

                # Listar os detalhes do usuário
                user_details_response = requests.get(
                    f"https://demoqa.com/Account/v1/User/{response.json()['userID']}",
                    headers={"Authorization": f"Bearer {token}"}
                )

                if user_details_response.status_code == 200:
                    user_details = user_details_response.json()
                    print("Detalhes do usuário:")
                    print(user_details)
                else:
                    print(f"Erro ao listar detalhes do usuário: {user_details_response.text}")
            else:
                print(f"Erro ao listar livros: {books_response.text}")
        else:
            print("Usuário não autorizado.")
    else:
        print(f"Erro ao gerar token: {token_response.text}")
else:
    print(f"Erro ao criar usuário: {response.text}")
