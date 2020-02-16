/*
 * Copyright 2017-2019 original authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.micronaut.docs

import io.micronaut.context.annotation.Requires
import io.micronaut.core.util.CollectionUtils
import io.micronaut.http.HttpResponse
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Produces
import io.micronaut.views.ModelAndView
import io.micronaut.views.View
import io.micronaut.views.rocker.RockerWritable
import io.reactivex.Single

import views.home

import static io.micronaut.http.HttpResponse.ok

@Requires(property = "spec.name", value = "rocker")
@Controller("/rocker")
class RockerController {

    @View("home")
    @Get("/")
    HttpResponse index() {
        return HttpResponse.ok(CollectionUtils.mapOf("loggedIn", true, "username", "sdelamo"));
    }

    @View("home.rocker.html")
    @Get("/pogo")
    HttpResponse<Person> pogo() {
        HttpResponse.ok(new Person(loggedIn: true, username: 'sdelamo'))
    }

    @Get("/home")
    HttpResponse<Person> home() {
        HttpResponse.ok(new Person(loggedIn: true, username: 'sdelamo'))
    }

    @View
    @Get("/static")
    public HttpResponse<RockerWritable> staticTemplate() {
        return ok(new RockerWritable(home.template(true, "sdelamo")));
    }

    @Produces(MediaType.TEXT_PLAIN)
    @View("home.rocker.html")
    @Get("/viewWithNoViewRendererForProduces")
    Person viewWithNoViewRendererForProduces() {
        new Person(loggedIn: true, username: 'sdelamo')
    }

    @View("home")
    @Get("/reactive")
    Single<Person> reactive() {
        return Single.just(new Person(loggedIn: true, username: 'sdelamo'))
    }

    @Get("/modelAndView")
    Single<ModelAndView> modelAndView() {
        ModelAndView modelAndView = new ModelAndView("home",
                new Person(loggedIn: true, username: 'sdelamo'))
        return Single.just(modelAndView)
    }

    @View("bogus")
    @Get("/bogus")
    HttpResponse<Person> bogus() {
        HttpResponse.ok(new Person(loggedIn: true, username: 'sdelamo'))
    }

    @View("/home")
    @Get("/nullbody")
    HttpResponse nullBody() {
        HttpResponse.ok()
    }
}
