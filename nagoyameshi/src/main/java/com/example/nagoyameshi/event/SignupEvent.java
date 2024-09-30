package com.example.nagoyameshi.event;

import org.springframework.context.ApplicationEvent;

import com.example.nagoyameshi.entity.User;

import lombok.Getter;

@Getter
public class SignupEvent extends ApplicationEvent {
  private User user;
  private String requestUrl;

  public SignupEvent(Object source, User user, String requestUrl) {
    // superでスーパークラス（親クラス）のコンストラクタを呼び出し、
    // イベントのソース（発生源）(Publisherクラスのインスタンス) を渡す
    super(source);

    // 会員登録したユーザーの情報（Userオブジェクト）とリクエストを受けたURL（https://ドメイン名/signup）を保持
    this.user = user;
    this.requestUrl = requestUrl;
  }
}
