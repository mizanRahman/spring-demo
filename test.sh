## save a new card
curl -X POST -H "Content-Type: application/json" -H "Cache-Control: no-cache" -d '{
    "balance": 0,
    "pan": "1231231233"
}' "http://localhost:8080/api/cards"

