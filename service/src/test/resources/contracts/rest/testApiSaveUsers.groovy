org.springframework.cloud.contract.spec.Contract.make {
    description("""
    test Task api
""")
    request {
        method 'POST'
        url '/api/users'
        body("""{"firstName":"B","lastName":"B"}"""
        )
        headers {
            contentType(applicationJson())
        }
    }
    response {
        status 200
    }
}