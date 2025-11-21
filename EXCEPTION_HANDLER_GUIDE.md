# Global Exception Handler & Standard API Response

## Overview

Hệ thống xử lý exception tập trung và chuẩn hóa API response cho toàn bộ ứng dụng.

## Cấu trúc Response chuẩn

### Success Response

```json
{
  "status": 200,
  "message": "Success",
  "data": { ... },
  "timestamp": "2025-11-21T14:50:00"
}
```

### Error Response

```json
{
  "status": 400,
  "message": "Error message",
  "timestamp": "2025-11-21T14:50:00"
}
```

### Validation Error Response

```json
{
  "status": 400,
  "message": "Validation failed",
  "errors": {
    "email": "Email must be valid",
    "name": "Name is required"
  },
  "timestamp": "2025-11-21T14:50:00"
}
```

## Custom Exceptions

### 1. ResourceNotFoundException (404)

```java
// Cách 1: Message tùy chỉnh
throw new ResourceNotFoundException("User not found");

// Cách 2: Với field name
throw new ResourceNotFoundException("User", "email", "test@example.com");
// => "User not found with email: 'test@example.com'"

// Cách 3: Với ID
throw new ResourceNotFoundException("Team", 123L);
// => "Team not found with id: 123"
```

### 2. BadRequestException (400)

```java
throw new BadRequestException("Invalid request parameters");
```

### 3. DuplicateResourceException (409)

```java
throw new DuplicateResourceException("User", "email", "test@example.com");
// => "User already exists with email: 'test@example.com'"
```

### 4. UnauthorizedException (401)

```java
throw new UnauthorizedException("Please login to continue");
// hoặc
throw new UnauthorizedException(); // "Unauthorized access"
```

### 5. ForbiddenException (403)

```java
throw new ForbiddenException("You don't have permission");
// hoặc
throw new ForbiddenException(); // "Access denied"
```

## Sử dụng ApiResponse

### Trong Controller

#### Success Response

```java
@GetMapping("/users/{id}")
public ResponseEntity<ApiResponse<UserDTO>> getUser(@PathVariable Long id) {
    UserDTO user = userService.findById(id);
    return ResponseEntity.ok(ApiResponse.success(user));
}
```

#### Success với custom message

```java
@PostMapping("/users")
public ResponseEntity<ApiResponse<UserDTO>> createUser(@Valid @RequestBody CreateUserRequest request) {
    UserDTO user = userService.create(request);
    return ResponseEntity.ok(ApiResponse.created("User created successfully", user));
}
```

#### Success không có data

```java
@DeleteMapping("/users/{id}")
public ResponseEntity<ApiResponse<Void>> deleteUser(@PathVariable Long id) {
    userService.delete(id);
    return ResponseEntity.ok(ApiResponse.success("User deleted successfully"));
}
```

## Automatic Exception Handling

Các exception sau được tự động handle bởi `GlobalExceptionHandler`:

### 1. Validation Errors (MethodArgumentNotValidException)

Tự động bắt khi dùng `@Valid` annotation:

```java
@PostMapping("/users")
public ResponseEntity<ApiResponse<UserDTO>> createUser(@Valid @RequestBody CreateUserRequest request) {
    // Nếu validation fail, tự động trả về:
    // {
    //   "status": 400,
    //   "message": "Validation failed",
    //   "errors": {
    //     "email": "Email must be valid",
    //     "name": "Name is required"
    //   }
    // }
}
```

### 2. Database Errors (DataAccessException)

Tự động bắt lỗi database:

- Duplicate entry
- Foreign key constraint
- Not null violation

### 3. Generic Errors (Exception)

Mọi exception chưa được handle sẽ trả về 500:

```json
{
  "status": 500,
  "message": "An unexpected error occurred. Please contact support."
}
```
