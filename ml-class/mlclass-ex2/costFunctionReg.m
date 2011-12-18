function [J, grad] = costFunctionReg(theta, X, y, lambda)
%COSTFUNCTIONREG Compute cost and gradient for logistic regression with regularization
%   J = COSTFUNCTIONREG(theta, X, y, lambda) computes the cost of using
%   theta as the parameter for regularized logistic regression and the
%   gradient of the cost w.r.t. to the parameters. 

% Initialize some useful values
m = length(y); % number of training examples

% You need to return the following variables correctly 
J = 0;
grad = zeros(size(theta));

% ====================== YOUR CODE HERE ======================
% Instructions: Compute the cost of a particular choice of theta.
%               You should set J to the cost.
%               Compute the partial derivatives and set grad to the partial
%               derivatives of the cost w.r.t. each parameter in theta


logError = (-1 .* y) .* log(sigmoid(X*theta)) - (1-y) .* log(1-sigmoid(X*theta));
J = (1/m) * sum(logError) + (lambda/(2*m))*sum(theta(2:length(theta),1).^2);
        
n = length(theta);
for k = 1:n
    error = 0;
    for i = 1:m
        error = error + ((sigmoid(sum(X(i,:)*theta)) - y(i,1)) * X(i,k));
    end
    grad(k,1) = 1/ m * error;
    if (k>1)
        grad(k,1) = grad(k,1) + (lambda/m) * theta(k,1);
    end
end



% =============================================================

end
