# easy-room
Sistema básico para reserva de quartos de hotel

## Endpoints

### Convenções:
- **URL Base:** `http://localhost:8080/`
- **Formato de Dados:** Todas as respostas e solicitações devem ser em JSON.

### 1. Listar Recursos (GET)

#### Recupera uma lista de todos os quartos disponíveis
```bash
GET /rooms
```

#### Recupera as informações de um quarto específico
```bash
GET /rooms/{id}
```

#### Recupera uma lista de todas as reservas disponíveis
```bash
GET /reservations
```

#### Recupera as informações de uma reserva específica
```bash
GET /reservations/{id}
```

### 2. Criar Recursos (POST)

#### Cria um novo quarto
```bash
POST /rooms
```

**Request Body**
```json
{
  "roomNumber": "42",
  "roomType": "SINGLE"
}
```

#### Cria uma nova reserva
```bash
POST /rooms
```

**Request Body**
```json
{
  "guestName": "Algacyr Melo",
  "checkInDate": "2024-12-21",
  "checkOutDate": "2024-12-28",
  "hotelRoomId": 1
}
```

### 3. Atualizar Recursos (PUT)

#### Atualiza as informações de um quarto
```bash
PUT /rooms
```

**Request Body**
```json
{
  "id": 1,
  "roomNumber": "42",
  "roomType": "SUITE"
}
```

#### Atualiza as informações de uma reserva
```bash
PUT /reservations
```

**Request Body**
```json
{
  "id": 1,
  "guestName": "Maria Santos"
}
```

### 4. Deletar Recursos (DELETE)

#### Deletar um quarto
```bash
DELETE /rooms/{id}
```

#### Deletar uma reserva
```bash
DELETE /reservations/{id}
```
