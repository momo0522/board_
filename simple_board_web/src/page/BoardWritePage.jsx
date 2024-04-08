import React, {useState} from 'react';
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

  const handleImageChange = (e) => {
    e.preventDefault();
    // 파일을 post 상태의 image 필드에 저장
    setPost(prevState => ({
      ...prevState,
      image: e.target.files[0] // 파일 선택
    }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    const boardId = await axios.post('http://localhost:8080/write', post)
        .then(function (response) {
          console.log(response);
          return response.data;
        })
        .catch(function (error) {
          console.log(error);
        });
    console.log('Submitted Post:', post);
    if (post.image){
        const formData = new FormData();
        formData.append('id', boardId);
        formData.append('image', post.image);

        axios.post('http://localhost:8080/view/image', formData,
            {
                'Content-Type': 'multipart/form-data'
            })
            .then(function (response) {
                console.log(response);
            })
            .catch(function (error) {
                console.log(error);
            });

    }
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
        <div>
          <label htmlFor="image">이미지 첨부:</label>
          <input
              type="file"
              id="image"
              name="image"
              onChange={handleImageChange}
          />
        </div>
        <button type="submit">글 작성</button>

      </form>
  );
}