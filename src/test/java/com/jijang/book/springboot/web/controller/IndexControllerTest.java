package com.jijang.book.springboot.web.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

import com.jijang.book.springboot.web.domain.posts.Posts;
import com.jijang.book.springboot.web.domain.posts.PostsRepository;
import com.jijang.book.springboot.web.dto.PostsResponseDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class IndexControllerTest {

  @Autowired
  private TestRestTemplate restTemplate;

  @Autowired
  private PostsRepository postsRepository;

  @Test
  public void 메인페이지_로딩() {
    //when
    String body = this.restTemplate.getForObject("/", String.class);

    //then
    assertThat(body).contains("스프링 부트로 시작하는 웹 서비스");
  }


  @Test
  public void 저장페이지_로딩() {
    //when
    String body = this.restTemplate.getForObject("/posts/save", String.class);

    //then
    assertThat(body).contains("게시글 등록");

  }

  @Test
  public void 수정페이지_로딩() {
    //given
    Posts savedPosts = postsRepository.save(Posts.builder().title("title").content("content").author("author").build());

    Long updatedId = savedPosts.getId();

    //when
    String body = this.restTemplate.getForObject("/posts/update/" + updatedId, String.class);

    //then
    assertThat(body).contains("게시글 수정");

  }
}


