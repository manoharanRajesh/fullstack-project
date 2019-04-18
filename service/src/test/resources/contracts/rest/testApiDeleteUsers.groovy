org.springframework.cloud.contract.spec.Contract.make {
    description("""
    test Task api
""")
    request {
        method 'POST'
        url '/api/users/delete'
        headers {
            contentType(applicationJson())
        }
        body("4")
    }
    response { status 200 }
}