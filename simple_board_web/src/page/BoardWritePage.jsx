import React, { useState } from 'react';
import { useNavigate } from "react-router-dom";
import axios from 'axios';

export function BoardWritePage() {
  const [post, setPost] = useState({
    writer: '',
    title: '',
    content: ''
  });
  const navigate = useNavigate();

  const handleChange = (e) => {
    e.preventDefault();
    const { name, value } = e.target;
    setPost(prevState => ({
      ...prevState,
      [name]: value
    }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    await axios.post('http://localhost:8080/write', post)
      .then(function (response) {
        console.log(response);
      })
      .catch(function (error) {
        console.log(error);
      });
    console.log('Submitted Post:', post);
    navigate('/');

  };

  return (
    <form onSubmit={handleSubmit}>
      <div>
        <label htmlFor="writer">글쓴이:</label>
        <input
          type="text"
          id="writer"
          name="writer"
          value={post.writer}
          onChange={handleChange}
        />
      </div>
      <div>
        <label htmlFor="title">제목:</label>
        <input
          type="text"
          id="title"
          name="title"
          value={post.title}
          onChange={handleChange}
        />
      </div>
      <div>
        <label htmlFor="content">내용:</label>
        <textarea
          id="content"
          name="content"
          rows="5"
          value={post.content}
          onChange={handleChange}
        ></textarea>
      </div>
      <button type="submit">글 작성</button>
    </form>
  );
}
