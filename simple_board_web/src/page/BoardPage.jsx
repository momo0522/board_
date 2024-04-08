import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { useNavigate, useParams  } from "react-router-dom";
import { Link } from 'react-router-dom';

export function BoardPage() {
  const [post, setPost] = useState({});
  const { id } = useParams(); // URL에서 id를 가져옴
    const navigate = useNavigate();

    useEffect(()=>{
    // 서버에서 글의 상세 정보를 가져오는 함수
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

  // 글 삭제 함수
  const deletePost = async () => {
    try {
      await axios.delete(`http://localhost:8080/view/${id}`);
      navigate("/");
    } catch (error) {
      console.error('글을 삭제하는 데 실패했습니다.', error);
    }
  };

  // 글 수정 페이지로 이동하는 함수
  const editPost = () => {

    navigate(`/update/${id}`);
  };

  return (
    <div>
      <h2>{post.title}</h2>
      <p>글쓴이: {post.writer}</p>
      <p>작성시간: {post.createdAt}</p>
      <p>내용: {post.content}</p>
      <div key={id} onClick={deletePost}>
          <Link to={`/`}>글 삭제</Link>
      </div>
      <button onClick={editPost}>글 수정</button>
    </div>
  );
}