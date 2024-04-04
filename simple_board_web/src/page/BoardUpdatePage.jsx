
import React, { useState, useEffect } from 'react';
import { useNavigate } from "react-router-dom";
import axios from 'axios';
import {useParams  } from "react-router-dom";
export function BoardUpdatePage() {
    const { id } = useParams();
  const [post, setPost] = useState({
    writer: '',
    title: '',
    content: ''
  });
  const navigate = useNavigate();

  useEffect(()=>{
    const fetchPost = async () => {
      try {
        const response = await axios.get(`http://localhost:8080/view/${id}`);
        setPost(response.data);
      } catch (error) {
        console.error('글을 불러오는 데 실패했습니다.', error);
      }
    };

    fetchPost();
  }, []);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setPost(prevState => ({
      ...prevState,
      [name]: value
    }));
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    axios.put(`http://localhost:8080/view/${id}`, post)
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
