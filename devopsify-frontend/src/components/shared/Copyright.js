import React from "react";

function Copyright(props) {
  return (
    <div style={{ color: props.color ? props.color : "rgba(15, 103, 8, 1)"}}>
      &copy; {new Date().getFullYear()} Copyright : {props.title}
    </div>
  );
}
export default Copyright;