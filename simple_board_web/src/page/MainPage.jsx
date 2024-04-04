import React, { useEffect, useState } from 'react';
import moment from 'moment';
import axios from 'axios';
import { Link } from 'react-router-dom';

import { useNavigate } from 'react-router-dom';

export function MainPage() {
  const [boards, setBoards] = useState([]); // 빈 배열로 초기화
  const [viewCount, setViewCount] = useState(10);
  const [currentPage, setCurrentPage] = useState(1);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await axios.get(`http://localhost:8080/view`);
        setBoards(response.data); // 가정: response.data는 배열
      } catch (error) {
        console.error('글을 불러오는 데 실패했습니다.', error);
      }
    };

    fetchData();
  }, []);

  // 컴포넌트 내부
  const navigate = useNavigate();
  
  const goToDetailPage = (id) => {
    navigate(`${id}`);
  };
  
  
  const handleViewCountChange = (e) => {
    setViewCount(Number(e.target.value));
    setCurrentPage(1);
  };

  const indexOfLastBoard = currentPage * viewCount;
  const indexOfFirstBoard = indexOfLastBoard - viewCount;
  const currentBoards = boards.slice(indexOfFirstBoard, indexOfLastBoard);

  const paginate = (pageNumber) => {
    setCurrentPage(pageNumber);
  };

  const PageNumbers = () => {
    const pageNumbers = [];
    for (let i = 1; i <= Math.ceil(boards.length / viewCount); i++) {
      pageNumbers.push(i);
    }

    return (
      <nav>
        <ul className='pagination'>
          {pageNumbers.map(number => (
            <li key={number} className='page-item'>
              <button onClick={() => paginate(number)} className='page-link'>
                {number}
              </button>
            </li>
          ))}
        </ul>
      </nav>
    );
  };
  return (
    <div>
      <h2>게시판</h2>
      <select value={viewCount} onChange={handleViewCountChange}>
        <option value="10">10개씩 보기</option>
        <option value="20">20개씩 보기</option>
        <option value="30">30개씩 보기</option>
      </select>
      <table>
        <thead>
          <tr>
            <th>작성자</th>
            <th>제목</th>
            <th>작성 일자</th>
          </tr>
        </thead>
        <tbody>
          {currentBoards.length > 0 ? (
            currentBoards.map((board) => (
              <tr key={board.id} onClick={() => goToDetailPage(board.id)} style={{cursor: 'pointer'}}>
                <td>{board.writer}</td>
                <td>{board.title}</td>
                <td>{moment(board.createdAt).format('YYYY-MM-DD')}</td>
              </tr>
            ))
          ) : (
            <tr>
              <td colSpan="3">게시글이 없습니다.</td>
            </tr>
          )}
        </tbody>
      </table>
      <PageNumbers />
      <Link to={`/write`}>글 작성</Link>
    </div>
  );
}
