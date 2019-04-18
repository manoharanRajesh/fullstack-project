org.springframework.cloud.contract.spec.Contract.make {
    description("""
    test Project api
""")
    request {
        method 'POST'
        url '/api/projects/delete'
        headers {
            contentType(applicationJson())
        }
        body("4")
    }
    response { status 200 }
}