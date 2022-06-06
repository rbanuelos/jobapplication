# Job Offer & Application app
Spring boot ReST API for managing job offers and job applications. It uses MySQL as main data store and Redis as a cache


## API
| URL | Method | Query Params | Path Params | Body  | Response  |
| :---: | :--: | :--: | :--: | :--: | :--: |
| /jobs | `GET` | tags={comma separated tags} | None | None | `[    {        "title": "Job Offer 1",        "description": "Job Offer 1 description",        "tags": "java, react, docker"    },    {        "title": "Job Offer 3",        "description": "Job Offer 3 description",        "tags": "spring, java, kubernetes"    }]` |
| /jobs/{id} | `GET` | None | id={job offer id} | None | `{        "title": "Job Offer 1",        "description": "Job Offer 1 description",        "tags": "java, react, docker"    }` |
| /jobs | `POST` | None | None | `{ "title" : "Job Offer 1","description" : "Job Offer 1 description","tags" : "tag1, tag2, tag3"}` | `id` |
| /jobs/{id}/applications | `GET` | None | id={job offer id} | None | `[    {        "fullName": "John Doe",        "address": "John Doe address",        "phone": "John Doe phone"    },    {        "fullName": "Jack E.",        "address": "Jack E. address",        "phone": "Jack E. phone"    }]` |
| /jobs/{id}/apply | `POST` | None | None | `{    "fullName" : "M.J.",    "address" : "M.J. address",    "phone" : "M.J. phone"}` | `boolean` |
